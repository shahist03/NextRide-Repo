package com.app.app.service.thirdParty;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public String sendSms(String toPhoneNumber, String message) {
        Message sms = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                message
        ).create();

        return "Message sent with ID: " + sms.getSid();
    }
}

