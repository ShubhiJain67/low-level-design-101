package projects.parkinglot.pricing;

import java.time.Duration;
import java.time.LocalDateTime;
import projects.parkinglot.parking.Ticket;

public class TimeBasedPayment implements IPricingStrategy {
    @Override 
    public double calculateAmount(Ticket ticket) {
        LocalDateTime exitTime = LocalDateTime.now();
        LocalDateTime entryTime = ticket.getTime();
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        return minutes*10 + 50;
    }
}
