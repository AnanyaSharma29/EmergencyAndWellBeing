package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.SosAlert;
import com.EmergencyAndMentalWellBeing.Backend.Service.SosAlertService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SosAlertServiceImpl implements SosAlertService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Override
    public boolean sendSosNotification(SosAlert sosAlert) {
        try {
            Twilio.init(accountSid, authToken);

            // Use only the message and phone number fields
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(sosAlert.getPhoneNumber()),
                    new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                    sosAlert.getMessage()
            ).create();

            System.out.println("SOS Alert sent successfully! Message SID: " + message.getSid());
            return true;
        } catch (Exception e) {
            System.err.println("Error sending SOS alert: " + e.getMessage());
            return false;
        }
    }
}
