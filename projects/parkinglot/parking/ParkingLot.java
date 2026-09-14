package projects.parkinglot.parking;

import java.util.ArrayList;
import java.util.List;
import projects.parkinglot.payment.PaymentModeType;
import projects.parkinglot.payment.PaymentProcessor;
import projects.parkinglot.pricing.PricingProcessor;
import projects.parkinglot.pricing.PricingStrategyEnum;

public class ParkingLot {
    private final int id;
    private final List<ParkingFloor> floors;
    private final PricingStrategyEnum strategy;
    private final PricingProcessor pricingProcessor;
    private final PaymentProcessor paymentProcessor;

    public ParkingLot(int id, PricingStrategyEnum strategy) {
        this.id = id;
        this.strategy = strategy;
        this.floors = new ArrayList<>();
        this.paymentProcessor = PaymentProcessor.getPaymentInstance();
        this.pricingProcessor = PricingProcessor.getPricingInstance();
    }

    public int getId() {
        return this.id;
    }

    public void addParkingFloor(ParkingFloor floor){
        this.floors.add(floor);
    }

    public synchronized Ticket parkVehicle(VehicleTypeEnum vehicleType){
        for (ParkingFloor floor : this.floors) {
            if(floor.hasEmptySpot(vehicleType)) {
                ParkingSpot spot = floor.parkVehicle(vehicleType);
                if(spot == null) {
                    continue;
                }
                return new Ticket(vehicleType, spot, floor);
            }
        }
        return null;
    }

    public synchronized void unparkVehicle(Ticket ticket, PaymentModeType mode){
        double amountToPay = this.pricingProcessor.calculateAmount(this.strategy, ticket);
        if(!ticket.isPaid()) {
            paymentProcessor.pay(mode, amountToPay);
            ticket.markPaid();
        }
        ticket.getFloor().unparkVehicle(ticket);
    }
}
