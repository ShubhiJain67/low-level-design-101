package projects.parkinglot.parking;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;
import projects.parkinglot.payment.PaymentStatusEnum;

/**
 * Ticket
 */
public class Ticket {
    private final VehicleTypeEnum type;
    private final ParkingSpot spot;
    private final ParkingFloor floor;
    private final LocalDateTime time;
    private final AtomicReference<PaymentStatusEnum> paymentStatus;

    public Ticket(VehicleTypeEnum type, ParkingSpot spot, ParkingFloor floor) {
        this.type = type;
        this.spot = spot;
        this.time = LocalDateTime.now();
        this.floor = floor;
        this.paymentStatus = new AtomicReference<>(PaymentStatusEnum.PENDING);
    }

    public VehicleTypeEnum getType() {
        return this.type;
    }

    public ParkingSpot getSpot() {
        return this.spot;
    }

    public ParkingFloor getFloor() {
        return this.floor;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return this.paymentStatus.get();
    }

    public boolean isPaid() {
        return this.getPaymentStatus() == PaymentStatusEnum.PAID;
    }

    public boolean markPaid() {
        this.paymentStatus.set(PaymentStatusEnum.PAID);
        return true;
    }

    @Override
    public String toString() {
        return "Ticket{" + "type=" + type + ", spot=" + spot.getId() + ", floor=" + floor.getId() + '}';
    }
}
