package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.SosAlert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.EmergencyAndMentalWellBeing.Backend.Service.SosAlertService;

@RestController
@RequestMapping("/api")
public class SosAlertController {

    @Autowired
    private SosAlertService sosAlertService;

    @PostMapping("/sos-alert")
    public ResponseEntity<String> sendSosAlert(@RequestBody SosAlert sosAlert) {
        // Validate input
        if (sosAlert.getPhoneNumber() == null || sosAlert.getMessage() == null) {
            return ResponseEntity.badRequest().body("Missing required fields: phone number or message.");
        }

        boolean success = sosAlertService.sendSosNotification(sosAlert);

        if (success) {
            return ResponseEntity.ok("SOS Alert Sent! Emergency contacts notified.");
        } else {
            return ResponseEntity.status(500).body("Failed to send SOS alert. Please try again.");
        }
    }
}
