package projects.movieticketbooking.models;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import projects.movieticketbooking.enums.*;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Map<Seat, AvailabilityStatus> seats;
    private final Map<Seat, ReentrantReadWriteLock > seatLocks;

    public Show(Movie movie, Screen screen, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;

        this.seats = new HashMap<>();
        this.seatLocks = new HashMap<>();
        List<Seat> screenSeats = screen.getSeats();
        for(Seat seat: screenSeats){
            this.seats.put(seat, AvailabilityStatus.UNRESERVED);
            this.seatLocks.put(seat, new ReentrantReadWriteLock());
        }
    }

    public String getId() {
        return this.id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public Map<Seat, AvailabilityStatus> getSeats(){
        return new HashMap<>(this.seats);
    }

    public void ReserveSeat(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found empty seat");
        }
        if(!this.seats.containsKey(seat)){
            throw new IllegalArgumentException("show does not own the seat");
        }
        if(this.seats.get(seat) != AvailabilityStatus.UNRESERVED){
            throw new IllegalStateException("seat is not available to get reserved");
        }
        this.seats.put(seat, AvailabilityStatus.RESERVED);
    }

    public void UnreserveSeat(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found empty seat");
        }
        if(!this.seats.containsKey(seat)){
            throw new IllegalArgumentException("show does not own the seat");
        }
        if(this.seats.get(seat) != AvailabilityStatus.RESERVED){
            throw new IllegalStateException("seat is not reserved to get unreserved");
        }
        this.seats.put(seat, AvailabilityStatus.UNRESERVED);
    }

    public boolean IsSeatReserved(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found empty seat");
        }
        if(!this.seats.containsKey(seat)){
            throw new IllegalArgumentException("show does not own the seat");
        }
        return this.seats.get(seat) == AvailabilityStatus.RESERVED;
    }

    public boolean IsSeatAvailable(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found empty seat");
        }
        if(!this.seats.containsKey(seat)){
            throw new IllegalArgumentException("show does not own the seat");
        }
        return this.seats.get(seat) == AvailabilityStatus.UNRESERVED;
    }

    public ReentrantReadWriteLock getSeatLock(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found empty seat");
        }
        if(!this.seats.containsKey(seat)){
            throw new IllegalArgumentException("show does not own the seat");
        }
        if(!this.seatLocks.containsKey(seat)){
            throw new IllegalArgumentException("seat lock not found");
        }
        return this.seatLocks.get(seat);
    }
}
