package projects.movieticketbooking.filters;

import java.util.ArrayList;
import java.util.List;
import projects.movieticketbooking.models.Screen;
import projects.movieticketbooking.models.Show;
import projects.movieticketbooking.models.Theater;

public class TheaterFilter implements IFilter {
    private final String theaterId;

    public TheaterFilter(String theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public List<Show> filter(List<Show> shows) {
        if (theaterId == null || theaterId.isBlank()) {
            return shows;
        }
        List<Show> filteredShows = new ArrayList<>();
        for (Show show : shows) {
            Screen screen = show.getScreen();
            if(screen == null){
                throw new IllegalStateException("found no screen for this show");
            }
            Theater theater = screen.getTheater();
            if(theater == null){
                throw new IllegalStateException("found no theater for this screen");
            }
            if(theater.getId().equals(theaterId)){
                filteredShows.add(show);
            }
        }
        return filteredShows;
    }
}
