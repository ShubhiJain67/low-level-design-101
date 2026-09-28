package projects.movieticketbooking;

import java.time.LocalDateTime;
import java.util.*;
import projects.movieticketbooking.enums.AvailabilityStatus;
import projects.movieticketbooking.filters.*;
import projects.movieticketbooking.models.*;
import projects.movieticketbooking.services.BookingService;
import projects.movieticketbooking.services.ListingService;
import projects.movieticketbooking.services.SearchService;

public class Main {
    private static BookingService bookingService;
    private static List<Theater> theaters;
    public static void main(String[] args) {
        start();
        User shubhi = new User("shubhi");

        int seatCount = 2;
        List<IFilter> filters = new ArrayList<>();
        filters.add(new TheaterFilter(theaters.get(0).getId()));
        List<Show> shows = bookingService.searchShows(filters);
        Show show = shows.get(0);
        Map<Seat, AvailabilityStatus> seatMap = bookingService.getSeatsForShow(show);

        List<Seat> selectedSeats = new ArrayList<>();
        for (Seat seat : seatMap.keySet()) {
            if(show.IsSeatAvailable(seat)){
                selectedSeats.add(seat);
                seatCount -= 1;
            }
            if(seatCount == 0){
                break;
            }
        }
        Reservation reservation = bookingService.holdSeats(shubhi, shows.get(0), selectedSeats);
        bookingService.confirmReservation(reservation);
    }

    private static void start(){
        ListingService listingService = new ListingService();
        SearchService searchService = new SearchService();
        bookingService = new BookingService(searchService, listingService);
        List<Show> shows = getShows();
        bookingService.setShows(shows);
    }

    private static List<Show> getShows() {
        theaters = new ArrayList<>();
        Theater theater1 = new Theater();
        theaters.add(theater1);
        Screen screen1 = new Screen(theater1);
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat(i);
            screen1.addSeat(seat);
        }
        Screen screen2 = new Screen(theater1);
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat(i);
            screen2.addSeat(seat);
        }

        Theater theater2 = new Theater();
        theaters.add(theater2);
        Screen screen3 = new Screen(theater2);
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat(i);
            screen3.addSeat(seat);
        }
        Screen screen4 = new Screen(theater2);
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat(i);
            screen4.addSeat(seat);
        }

        Movie movie1 = new Movie("Yeh jawani hai deewani");
        Movie movie2 = new Movie("Taare zameen par");

        Show show1 = new Show(movie1, screen1, LocalDateTime.now().plusHours(200), LocalDateTime.now().plusHours(203));
        Show show2 = new Show(movie1, screen1, LocalDateTime.now().plusHours(204), LocalDateTime.now().plusHours(207));
        Show show3 = new Show(movie1, screen2, LocalDateTime.now().plusHours(200), LocalDateTime.now().plusHours(203));
        Show show4 = new Show(movie2, screen3, LocalDateTime.now().plusHours(200), LocalDateTime.now().plusHours(203));
        Show show5 = new Show(movie2, screen4, LocalDateTime.now().plusHours(200), LocalDateTime.now().plusHours(203));
        return new ArrayList<>(List.of(show1, show2, show3, show4, show5));
    }
}
