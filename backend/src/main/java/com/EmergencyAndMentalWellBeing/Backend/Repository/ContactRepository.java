package com.EmergencyAndMentalWellBeing.Backend.Repository;

import com.EmergencyAndMentalWellBeing.Backend.Model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
