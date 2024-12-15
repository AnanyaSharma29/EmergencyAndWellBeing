package com.EmergencyAndMentalWellBeing.Backend.Repository;

import com.EmergencyAndMentalWellBeing.Backend.Model.PaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentMethodRepo extends MongoRepository<PaymentMethod,String> {
    List<PaymentMethod>findByUserId(int userId);
}
