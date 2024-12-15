package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    // Register API
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        // Check if all required fields are present
        if (user.getFirstname() == null || user.getFirstname().isEmpty() ||
                user.getLastname() == null || user.getLastname().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPhonenumber() == null || user.getPhonenumber().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        // Set the role (if not set already)
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("User"); // Default role if not specified
        }

        // Save the user to the database
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    // Login API - Modify to use email instead of username
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");  // Get email from login data
        String password = loginDetails.get("password");

        // Find user by email instead of username
        Users user = userRepository.findByEmail(email);  // Use email to fetch user

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }

        // Successful login
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful.");
        response.put("role", user.getRole()); // Return user role or any other info

        return ResponseEntity.ok(response);
    }
}