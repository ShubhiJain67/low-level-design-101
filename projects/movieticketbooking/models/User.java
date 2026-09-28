package projects.movieticketbooking.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private final List<Reservation> reservations;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.reservations = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(this.reservations);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
