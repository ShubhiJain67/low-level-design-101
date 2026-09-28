package projects.locker.services;

import java.time.LocalDate;
import java.util.*;
import projects.locker.enums.UserType;
import projects.locker.models.*;

public class LockerService {
    private final AvailabilityService availabilityService;
    private final OTPService otpService;

    public LockerService(AvailabilityService availabilityService, OTPService otpService) {
        this.availabilityService = availabilityService;
        this.otpService = otpService;
    }

    public Locker getAvailableLocker(LockerStation station, Parcel parcel, User customer){
        if(station == null || parcel == null || customer == null){
            throw new IllegalArgumentException("station | parcel | customer cannot be null");
        }
        if(customer.getType() != UserType.CUSTOMER){
            throw new IllegalArgumentException("found invalid customer");
        }
        List<Locker> availableLockers = station.getAvailableLockers();
        Locker locker = this.availabilityService.selectAvailableLocker(availableLockers, parcel, customer);
        return locker;
    }

    public OTP depositParcel(LockerStation station, Parcel parcel, Locker locker, User deliveryPartner, User customer){
        if(station == null || locker == null || parcel == null || deliveryPartner == null || customer == null){
            throw new IllegalArgumentException("station | locker | parcel | delivery partner | customer cannot be null");
        }
        if(deliveryPartner.getType() != UserType.DELIVERY_DRIVER){
            throw new IllegalArgumentException("found invalid delivery partner");
        }
        if(customer.getType() != UserType.CUSTOMER){
            throw new IllegalArgumentException("found invalid customer");
        }
        if(!station.containsLocker(locker)){
            throw new IllegalArgumentException("locker does not belong tp the station");
        }
        if(!locker.isAvailable()){
            throw new IllegalStateException("locker is not available");
        }
        if(parcel.isLockerAssigned()){
            throw new IllegalStateException("locker is already assigned to the parcel");
        }
        OTP otp = this.otpService.getOTP();
        station.occupyLocker(locker);
        parcel.assignLocker(locker);
        parcel.assignOTP(otp);
        parcel.assignDepositDate(LocalDate.now());
        parcel.assignCustomer(customer);
        locker.assignParcel(parcel);
        customer.addParcel(parcel);
        return otp;
    }

    public void collectParcel(Parcel parcel, OTP otp, User customer){
        if(parcel == null || customer == null || otp == null){
            throw new IllegalArgumentException("otp | parcel | customer cannot be null");
        }
        if(customer.getType() != UserType.CUSTOMER){
            throw new IllegalArgumentException("found invalid customer");
        }
        if(!parcel.isLockerAssigned()){
            throw new IllegalStateException("parcel is not assigned to any locker");
        }
        if(parcel.getOTP() == null){
            throw new IllegalStateException("parcel has no OTP");
        }
        if(otp.isExpired()){
            throw new IllegalStateException("otp is expired cannot be collected");
        }
        if(!otp.isSame(parcel.getOTP())){
            throw new IllegalStateException("invalid OTP");
        }
        User assignedCustomer = parcel.getCustomer();
        assignedCustomer.removeParcel(parcel);
        Locker assignedLocker = parcel.getLocker();
        assignedLocker.unassignParcel();
        LockerStation station = assignedLocker.getLockerStation();
        station.emptyLocker(assignedLocker);
        parcel.unassignLocker();
        parcel.unassignDepositDate();
        parcel.unassignOTP();
        parcel.unassignCustomer();
    }

    public void clearLocker(Locker locker, User staff){
        if(locker == null || staff == null){
            throw new IllegalArgumentException("staff | locker cannot be null");
        }
        if(staff.getType() != UserType.STAFF){
            throw new IllegalArgumentException("found invalid staff");
        }
        Parcel parcel = locker.getParcel();
        if(parcel == null){
            throw new IllegalStateException("locker is already empty, nothing to clear");
        }
        User assignedCustomer = parcel.getCustomer();
        assignedCustomer.removeParcel(parcel);
        Locker assignedLocker = parcel.getLocker();
        assignedLocker.unassignParcel();
        LockerStation station = assignedLocker.getLockerStation();
        station.emptyLocker(assignedLocker);
        parcel.unassignLocker();
        parcel.unassignDepositDate();
        parcel.unassignOTP();
        parcel.unassignCustomer();
    }
}
