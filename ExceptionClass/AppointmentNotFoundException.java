
package com.EmergencyAndMentalWellBeing.Backend.ExceptionClass;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
