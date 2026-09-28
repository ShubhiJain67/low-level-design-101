package projects.movieticketbooking.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import projects.movieticketbooking.enums.ReservationStatus;

public class Reservation {
    private final String id;
    private final User user;
    private final Show show;
    private final List<Seat> seats;
    private ReservationStatus status;
    private final LocalDateTime expiresAt;

    public Reservation(User user, Show show, List<Seat> seats) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.status = ReservationStatus.HELD;
        this.expiresAt = LocalDateTime.now().plusMinutes(10);
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Show getShow() {
        return this.show;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public ReservationStatus getStatus() {
        return this.status;
    }

    public LocalDateTime getExpiresAt() {
        return this.expiresAt;
    }

    public boolean isExpired() {
        return this.status == ReservationStatus.HELD && this.expiresAt != null && LocalDateTime.now().isAfter(this.expiresAt);
    }

    public void confirm() {
        if(this.status != ReservationStatus.HELD){
            throw new IllegalStateException("reservation is not held to get confirmed");
        }
        if(this.isExpired()){
            throw new IllegalArgumentException("reservation got expired hence cannot be booked");
        }
        this.reserveSeats(seats);
        this.status = ReservationStatus.CONFIRMED;
    }

    public void complete() {
        if(this.status != ReservationStatus.CONFIRMED){
            throw new IllegalStateException("a confirmed reservation can only be completed");
        }
        this.unreserveSeats(seats);
        this.status = ReservationStatus.COMPLETED;
    }

    public void cancel() {
        if(this.status == ReservationStatus.COMPLETED){
            throw new IllegalStateException("completed reservation cannot be cancelled");
        }
        if(this.status == ReservationStatus.CANCELLED){
            throw new IllegalStateException("cancelled reservation cannot be cancelled");
        }
        if(this.status == ReservationStatus.CONFIRMED){
            this.unreserveSeats(seats);
        }
        this.status = ReservationStatus.CANCELLED;
    }

    public void reserveSeats(List<Seat> seats){
        List<Seat> sortedSeats = new ArrayList<>(seats);
        sortedSeats.sort((a, b) -> Integer.compare(a.getSeatNumber(), b.getSeatNumber()));
        List<ReentrantReadWriteLock> acquiredLocks = new ArrayList<>();
        try {
            for(Seat seat : sortedSeats){
                ReentrantReadWriteLock seatLock = show.getSeatLock(seat);
                seatLock.writeLock().lock();
                acquiredLocks.add(seatLock);
            }
            for(Seat seat : sortedSeats){
                if(show.IsSeatReserved(seat)){
                    throw new IllegalArgumentException("1 or more seats are already reserved");
                }
            }
            for(Seat seat : sortedSeats){
                show.ReserveSeat(seat);
            }
        } finally {
            for(int i = acquiredLocks.size() - 1; i >= 0; i--){
                acquiredLocks.get(i).writeLock().unlock();
            }
        }
    }

    public void unreserveSeats(List<Seat> seats){
        List<Seat> sortedSeats = new ArrayList<>(seats);
        sortedSeats.sort((a, b) -> Integer.compare(a.getSeatNumber(), b.getSeatNumber()));
        List<ReentrantReadWriteLock> acquiredLocks = new ArrayList<>();
        try {
            for(Seat seat : sortedSeats){
                ReentrantReadWriteLock seatLock = show.getSeatLock(seat);
                seatLock.writeLock().lock();
                acquiredLocks.add(seatLock);
            }
            for(Seat seat : sortedSeats){
                if(show.IsSeatReserved(seat)){
                    show.UnreserveSeat(seat);
                }
            }
        } finally {
            for(int i = acquiredLocks.size() - 1; i >= 0; i--){
                acquiredLocks.get(i).writeLock().unlock();
            }
        }
    }
}
