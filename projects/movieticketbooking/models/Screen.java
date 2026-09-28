package projects.movieticketbooking.models;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Screen {
    private final String id;
    private final Theater theater;
    private final List<Seat> seats;

    public Screen(Theater theater) {
        this.id = UUID.randomUUID().toString();
        this.seats = new CopyOnWriteArrayList<>();
        this.theater = theater;
        if(theater != null){
            theater.addScreen(this);
        }
    }

    public String getId() {
        return this.id;
    }

    public Theater getTheater(){
        return this.theater;
    }

    public boolean hasSeat(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found an empty seat");
        }
        return this.seats.contains(seat);
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public void addSeat(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found an empty screen");
        }
        if(this.hasSeat(seat)){
            throw new IllegalStateException("Screen already has this seat");
        }
        this.seats.add(seat);
    }

    public void removeSeat(Seat seat){
        if(seat == null){
            throw new IllegalArgumentException("found an empty seat");
        }
        if(!this.hasSeat(seat)){
            throw new IllegalStateException("Theater does not have this seat");
        }
        this.seats.remove(seat);
    }
}
