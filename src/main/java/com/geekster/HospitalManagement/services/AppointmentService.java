package com.geekster.HospitalManagement.services;

import com.geekster.HospitalManagement.models.Appointment;
import com.geekster.HospitalManagement.models.AppointmentKey;
import com.geekster.HospitalManagement.repositories.IAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepo appointmentRepo;

    public void bookAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    public void cancelAppointment(AppointmentKey key) {
        appointmentRepo.deleteById(key);
    }
}
