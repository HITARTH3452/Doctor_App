package com.geekster.HospitalManagement.repositories;

import com.geekster.HospitalManagement.models.Appointment;
import com.geekster.HospitalManagement.models.AppointmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment, AppointmentKey> {
   // public String findByAppId(Long id);
}

