package com.EmergencyAndMentalWellBeing.Backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSOSAlertEmail(String to, double lat, double lon) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SOS Alert");

        // Create the Google Maps link
        String googleMapsLink = "https://www.google.com/maps?q=" + lat + "," + lon;

        // Construct the message with the location and Google Maps link
        String messageBody = "SOS Alert! Emergency at the following location:\n" +
                "Latitude: " + lat + "\n" +
                "Longitude: " + lon + "\n\n" +
                "View the location on Google Maps: " + googleMapsLink;

        message.setText(messageBody);
        emailSender.send(message);
    }
}
