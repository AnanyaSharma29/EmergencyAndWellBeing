package com.EmergencyAndMentalWellBeing.Backend.Repository;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, String> {
    Users findByUsername(String username);// Existing method
    Users findByEmail(String email);  // Add this method for email-based login
}
