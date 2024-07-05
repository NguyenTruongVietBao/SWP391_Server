package com.math.mathcha.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private static final String ACCOUNT_SID = "AC4088d38a54e39ff56488d8c7d7efcda1";
    private static final String AUTH_TOKEN = "your_auth_token";
    private Map<String, String> otpData = new HashMap<>();

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public String generateOtp(String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        otpData.put(phoneNumber, otp);
        sendSms(phoneNumber, otp);
        return otp;
    }

    public boolean validateOtp(String phoneNumber, String otp) {
        if (otpData.containsKey(phoneNumber) && otpData.get(phoneNumber).equals(otp)) {
            otpData.remove(phoneNumber);
            return true;
        }
        return false;
    }

    private void sendSms(String phoneNumber, String otp) {
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber("your_twilio_phone_number"),
                "Your OTP code is: " + otp
        ).create();
    }
}
