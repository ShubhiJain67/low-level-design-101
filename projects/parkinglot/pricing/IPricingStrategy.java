package projects.parkinglot.pricing;

import projects.parkinglot.parking.Ticket;

/**
 * IPricingStrategy
 */
public interface IPricingStrategy {
    abstract double calculateAmount(Ticket ticket);
}
