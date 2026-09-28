package projects.locker.models;

import java.time.LocalDate;
import java.util.UUID;

public class OTP {
    private final String id;
    private final LocalDate expirationDate;

    public OTP(){
        this.id = UUID.randomUUID().toString();
        this.expirationDate = LocalDate.now().plusDays(7);
    }

    public String getId(){
        return this.id;
    }

    public boolean isExpired(){
        LocalDate curr = LocalDate.now();
        return curr.isAfter(this.expirationDate);
    }

    public boolean isSame(OTP otp){
        return (otp.getId() == null ? this.getId() == null : otp.getId().equals(this.getId()));
    }
}
