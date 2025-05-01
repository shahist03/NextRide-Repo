package com.app.app.service.thirdParty;



import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Value("${twilio.whatsapp.number}")
    private String fromWhatsAppNumber;

    public String sendWhatsAppMessage(String toPhoneNumber, String message) {
        Message whatsappMessage = Message.creator(
                new PhoneNumber("whatsapp:" + toPhoneNumber),
                new PhoneNumber(fromWhatsAppNumber),
                message
        ).create();

        return "WhatsApp message sent with ID: " + whatsappMessage.getSid();
    }
}
