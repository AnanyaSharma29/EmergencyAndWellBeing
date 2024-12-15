package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Contact;
import com.EmergencyAndMentalWellBeing.Backend.Service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/emergency-contacts")
 // Allow CORS for all origins; adjust as per your requirements
public class ContactController {

    @Autowired
    private ContactService contactService;

    // 1. Get All Contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        log.info("Fetched all contacts. Total: {}", contacts.size());
        return ResponseEntity.ok(contacts);
    }

    // 2. Add a New Contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        // Custom Validation
        if (contact == null) {
            log.warn("Received null contact.");
            return ResponseEntity.badRequest().body(null);
        }
        if (contact.getName() == null || contact.getName().isEmpty()) {
            log.warn("Contact name is missing or empty.");
            return ResponseEntity.badRequest().body(null);
        }
        if (contact.getContactNumber() == null || contact.getContactNumber().length() < 10) {
            log.warn("Invalid contact number: {}", contact.getContactNumber());
            return ResponseEntity.badRequest().body(null);
        }
        if (contact.getEmail() == null || !contact.getEmail().contains("@")) {
            log.warn("Invalid email format: {}", contact.getEmail());
            return ResponseEntity.badRequest().body(null);
        }

        Contact savedContact = contactService.addContact(contact);
        log.info("New contact added: {}", contact.getName());
        return ResponseEntity.ok(savedContact);
    }

    // 3. Delete a Contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable String id) {
        log.info("Attempting to delete contact with ID: {}", id);
        boolean isDeleted = contactService.deleteContact(id);
        if (isDeleted) {
            log.info("Contact with ID {} deleted successfully.", id);
            return ResponseEntity.ok("Contact deleted successfully.");
        } else {
            log.warn("Contact with ID {} not found or could not be deleted.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found or could not be deleted.");
        }
    }

    // 4. Send SOS Alert
    @PostMapping("/send-sos")
    public ResponseEntity<String> sendSosAlertToContacts(@RequestBody String message) {
        if (message == null || message.trim().isEmpty()) {
            log.warn("SOS message is empty.");
            return ResponseEntity.badRequest().body("Message cannot be empty.");
        }

        log.info("Sending SOS message to all contacts.");
        contactService.sendSosToAllContacts(message);
        log.info("SOS message sent successfully.");
        return ResponseEntity.ok("SOS sent to all emergency contacts.");
    }
}
