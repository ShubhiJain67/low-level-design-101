package projects.locker.services;

import projects.locker.enums.UserType;
import projects.locker.models.*;

public class OTPService {
    public OTP getOTP(){
        return new OTP();
    }

    public void messageOTP(User user, OTP otp){
        if(user == null || otp == null){
            throw new IllegalArgumentException("user and otp cannot be null");
        }
        if(user.getType() != UserType.CUSTOMER){
            throw new IllegalArgumentException("otp can be sent only to a customer found " +user.getType());
        }
    }
    
    public boolean isOTPExpired(OTP otp){
        if(otp == null){
            throw new IllegalArgumentException("otp cannot be null");
        }
        return otp.isExpired();
    }
}
