package projects.movieticketbooking.filters;

import java.util.List;

import projects.movieticketbooking.models.Show;

public interface IFilter {
    List<Show> filter(List<Show> shows);
}
