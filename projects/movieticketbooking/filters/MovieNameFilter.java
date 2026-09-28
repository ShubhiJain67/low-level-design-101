package projects.movieticketbooking.filters;

import java.util.ArrayList;
import java.util.List;
import projects.movieticketbooking.models.Movie;
import projects.movieticketbooking.models.Show;

public class MovieNameFilter implements IFilter {
    private final String query;

    public MovieNameFilter(String query) {
        this.query = query;
    }

    @Override
    public List<Show> filter(List<Show> shows) {
        if (query == null || query.isBlank()) {
            return shows;
        }
        String needle = query.toLowerCase();
        List<Show> filteredShows = new ArrayList<>();
        for (Show show : shows) {
            Movie movie = show.getMovie();
            if(movie == null){
                throw new IllegalStateException("found no movie for this show");
            }
            if(movie.getName().toLowerCase().contains(needle)){
                filteredShows.add(show);
            }
        }
        return filteredShows;
    }
}
