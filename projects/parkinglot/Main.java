package projects.parkinglot;

import projects.parkinglot.parking.ParkingFloor;
import projects.parkinglot.parking.ParkingLot;
import projects.parkinglot.parking.ParkingSpot;
import projects.parkinglot.parking.Ticket;
import projects.parkinglot.parking.VehicleTypeEnum;
import projects.parkinglot.payment.PaymentModeType;
import projects.parkinglot.pricing.PricingStrategyEnum;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(1, PricingStrategyEnum.TIME_BASED);
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addParkingSpot(new ParkingSpot(1, VehicleTypeEnum.CAR));
        floor1.addParkingSpot(new ParkingSpot(2, VehicleTypeEnum.AUTO));
        floor1.addParkingSpot(new ParkingSpot(3, VehicleTypeEnum.BIKE));
        floor1.addParkingSpot(new ParkingSpot(4, VehicleTypeEnum.TRUCK));


        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addParkingSpot(new ParkingSpot(5, VehicleTypeEnum.CAR));
        floor2.addParkingSpot(new ParkingSpot(6, VehicleTypeEnum.AUTO));
        floor2.addParkingSpot(new ParkingSpot(7, VehicleTypeEnum.BIKE));
        floor2.addParkingSpot(new ParkingSpot(8, VehicleTypeEnum.TRUCK));
        
        lot.addParkingFloor(floor1);
        lot.addParkingFloor(floor2);

        Ticket ticket1 = lot.parkVehicle(VehicleTypeEnum.AUTO);
        if(ticket1 != null) {
            System.err.println("Parked Auto at " + ticket1.toString());
        } else {
            System.err.println("Found no place to place Auto 1");
        }
        Ticket ticket2 = lot.parkVehicle(VehicleTypeEnum.AUTO);
        if(ticket2 != null) {
            System.err.println("Parked Auto at " + ticket2.toString());
        } else {
            System.err.println("Found no place to place Auto 2");
        }
        Ticket ticket3 = lot.parkVehicle(VehicleTypeEnum.AUTO);
        if(ticket3 != null) {
            System.err.println("Parked Auto at " + ticket3.toString());
        } else {
            System.err.println("Found no place to place Auto 3");
        }

        lot.unparkVehicle(ticket1, PaymentModeType.CASH);
        ticket3 = lot.parkVehicle(VehicleTypeEnum.AUTO);
        if(ticket3 != null) {
            System.err.println("Parked Auto at " + ticket3.toString());
        } else {
            System.err.println("Found no place to place Auto 3");
        }
    }
}
