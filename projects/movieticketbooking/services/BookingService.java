package projects.movieticketbooking.services;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import projects.movieticketbooking.enums.*;
import projects.movieticketbooking.exceptions.*;
import projects.movieticketbooking.filters.*;
import projects.movieticketbooking.models.*;


public class BookingService {
    private List<Show> shows;
    private final SearchService searchService;
    private final ListingService listingService;

    public BookingService(SearchService searchService, ListingService listingService) {
        this.searchService = searchService;
        this.listingService = listingService;
    }

    public void setShows(List<Show> shows){
        this.shows = new CopyOnWriteArrayList<>(shows);
    }


    public List<Show> searchShows(List<IFilter> filters) {
        return searchService.search(this.shows, filters);
    }

    public Map<Seat, AvailabilityStatus> getSeatsForShow(Show show) {
        if (show == null) {
            throw new InvalidArgumentException("Found empty show");
        }
        return listingService.getSeatMap(show);
    }

    public List<Reservation> getBookingsForUser(User user) {
        if (user == null) {
            throw new InvalidArgumentException("Found empty user");
        }
        return user.getReservations();
    }

    public Reservation holdSeats(User user, Show show, List<Seat> seats){
        if(user == null || show == null || seats == null || seats.isEmpty()){
            throw new IllegalArgumentException("user, show or seats cannot be empty");
        }
        Reservation reservation = new Reservation(user, show, seats);
        user.addReservation(reservation);
        return reservation;
    }
    
    public void confirmReservation(Reservation reservation){
        if(reservation == null){
            throw new IllegalArgumentException("reservation cannot be empty");
        }
        reservation.confirm();
    }

    public void completeReservation(Reservation reservation){
        if(reservation == null){
            throw new IllegalArgumentException("reservation cannot be empty");
        }
        reservation.complete();
    }

    public void cancelReservation(Reservation reservation){
        if(reservation == null){
            throw new IllegalArgumentException("reservation cannot be empty");
        }
        reservation.cancel();
    }
}