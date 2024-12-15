package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.SosAlert;

public interface SosAlertService {
    boolean sendSosNotification(SosAlert sosAlert);
}

