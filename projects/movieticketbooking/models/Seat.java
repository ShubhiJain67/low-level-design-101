package projects.movieticketbooking.models;

import java.util.UUID;

public class Seat {
    private final String id;
    private int seatNumber;

    public Seat(int seatNumber) {
        this.id = UUID.randomUUID().toString();
        this.seatNumber = seatNumber;
    }

    public String getId() {
        return this.id;
    }
    
    public int getSeatNumber() {
        return this.seatNumber;
    }
}
