package projects.locker.models;

import java.time.LocalDate;
import java.util.UUID;
import projects.locker.enums.Size;


public class Parcel {
    private final String id;
    private final Size size;
    private Locker locker;
    private OTP otp;
    private LocalDate depositDate;
    private User customer;

    public Parcel(Size size) {
        this.id = UUID.randomUUID().toString();
        this.size = size;
        this.locker = null;
        this.otp = null;
        this.depositDate = null;
        this.customer = null;
    }

    public String getId(){
        return this.id;
    }

    public Size getSize(){
        return this.size;
    }

    public Locker getLocker(){
        return this.locker;
    }

    public User getCustomer(){
        return this.customer;
    }

    public void assignCustomer(User customer){
        this.customer = customer;
    }

    public void unassignCustomer(){
        this.customer = null;
    }

    public boolean isLockerAssigned(){
        return this.locker != null;
    }

    public void assignLocker(Locker locker){
        this.locker = locker;
    }

    public void unassignLocker(){
        this.locker = null;
    }

    public OTP getOTP(){
        return this.otp;
    }

    public void assignOTP(OTP otp){
        this.otp = otp;
    }

    public void unassignOTP(){
        this.otp = null;
    }

    public LocalDate getDepositDate(){
        return this.depositDate;
    }

    public void assignDepositDate(LocalDate depositDate){
        this.depositDate = depositDate;
    }

    public void unassignDepositDate(){
        this.depositDate = null;
    }
}
