package projects.movieticketbooking.services;

import java.util.Map;
import projects.movieticketbooking.enums.AvailabilityStatus;
import projects.movieticketbooking.models.Seat;
import projects.movieticketbooking.models.Show;

public class ListingService {
    public Map<Seat, AvailabilityStatus> getSeatMap(Show show) {
        return show.getSeats();
    }
}
