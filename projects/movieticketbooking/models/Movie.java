package projects.movieticketbooking.models;

import java.util.*;

public class Movie {
    private final String id;
    private final String name;
    private final List<Screen> screenings;

    public Movie(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.screenings = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Screen> getScreenings(){
        return this.screenings;
    }

    public void addScreening(Screen screen){
        this.screenings.add(screen);
    }

    public void removeScreening(Screen screen){
        this.screenings.remove(screen);
    }
}
