package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.PaymentMethod;
import com.EmergencyAndMentalWellBeing.Backend.Repository.PaymentMethodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PaymentMethod")
public class PaymentController {
    @Autowired
    PaymentMethodRepo paymentMethodRepo;

    @PostMapping
    public PaymentMethod addPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodRepo.save(paymentMethod);  // Save payment method to MongoDB
    }
    @GetMapping("/Users/{userId}")
    public List<PaymentMethod> getPaymentMethodsByUserId(@PathVariable int userId) {
        return paymentMethodRepo.findByUserId(userId);  // Get payment methods by user ID
    }

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepo.findAll();  // Get all payment methods
    }

}
