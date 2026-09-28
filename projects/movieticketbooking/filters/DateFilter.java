package projects.movieticketbooking.filters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import projects.movieticketbooking.models.Show;

public class DateFilter implements IFilter {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public DateFilter(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Show> filter(List<Show> shows) {
        if (from == null && to == null) {
            return shows;
        }
        List<Show> filteredShows = new ArrayList<>();
        for (Show show : shows) {
            if(from != null && show.getStartTime().isBefore(from)){
                continue;
            }
            if(to != null && show.getStartTime().isAfter(to)){
                continue;
            }
            filteredShows.add(show);
        }
        return filteredShows;
    }
}
