package projects.movieticketbooking.services;

import java.util.List;
import projects.movieticketbooking.filters.IFilter;
import projects.movieticketbooking.models.Show;

public class SearchService {
    public List<Show> search(List<Show> shows, List<IFilter> filters) {
        for (IFilter filter : filters) {
            shows = filter.filter(shows);
        }
        return shows;
    }
}
