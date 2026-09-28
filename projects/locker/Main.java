package projects.locker;

import projects.locker.enums.*;
import projects.locker.models.*;
import projects.locker.services.*;

public class Main {
    private static LockerService lockerService;
    public static void main(String[] args) {
        start();
        LockerStation station = new LockerStation();
        Locker smallLocker = new Locker(Size.SMALL, station);
        Locker mediumLocker = new Locker(Size.MEDIUM, station);
        Locker largeLocker = new Locker(Size.LARGE, station);
        station.addLocker(smallLocker);
        station.addLocker(mediumLocker);
        station.addLocker(largeLocker);

        Parcel smallParcel = new Parcel(Size.SMALL);
        Parcel mediumParcel = new Parcel(Size.MEDIUM);
        Parcel largeParcel = new Parcel(Size.LARGE);

        User deliveryPartner = new User("Gullu", UserType.DELIVERY_DRIVER, UserLevel.GOLD);
        User goldCustomer = new User("Shubhi", UserType.CUSTOMER, UserLevel.GOLD);
        User diamondCustomer = new User("Prateek", UserType.CUSTOMER, UserLevel.DIAMOND);
        // User staff = new User("Akku", UserType.STAFF, UserLevel.GOLD);

        Locker selectedLargeLocker = lockerService.getAvailableLocker(station, largeParcel, goldCustomer);
        OTP largeLockerOTP = null;
        if(selectedLargeLocker != null){
            largeLockerOTP = lockerService.depositParcel(station, largeParcel, selectedLargeLocker, deliveryPartner, goldCustomer);
        }

        Locker selectedMediumLocker = lockerService.getAvailableLocker(station, mediumParcel, goldCustomer);
        OTP mediumLockerOTP = null;
        if(selectedMediumLocker != null){
            mediumLockerOTP = lockerService.depositParcel(station, mediumParcel, selectedMediumLocker, deliveryPartner, goldCustomer);
        }

        Locker selectedSmallLocker = lockerService.getAvailableLocker(station, smallParcel, diamondCustomer);
        OTP smallLockerOTP = null;
        if(selectedSmallLocker != null){
            smallLockerOTP = lockerService.depositParcel(station, smallParcel, selectedSmallLocker, deliveryPartner, goldCustomer);
        }
        lockerService.collectParcel(largeParcel, largeLockerOTP, diamondCustomer);
        lockerService.collectParcel(largeParcel, largeLockerOTP, diamondCustomer);
        lockerService.collectParcel(mediumParcel, mediumLockerOTP, goldCustomer);
        lockerService.collectParcel(smallParcel, smallLockerOTP, goldCustomer);
    }

    private static void start(){
        AvailabilityService availabilityService = new AvailabilityService();
        OTPService otpService = new OTPService();
        lockerService = new LockerService(availabilityService, otpService);
    }
}
