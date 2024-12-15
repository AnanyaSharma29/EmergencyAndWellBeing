package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.SelfAssessment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SelfAssessmentController {

    @PostMapping("/self-assessment")
    public ResponseEntity<String> submitSelfAssessment(@RequestBody SelfAssessment selfAssessment) {
        // For now, log the data (or store it in a database)
        System.out.println("Received self-assessment: " + selfAssessment);
        return ResponseEntity.ok("Self-assessment submitted successfully!");
    }
}

