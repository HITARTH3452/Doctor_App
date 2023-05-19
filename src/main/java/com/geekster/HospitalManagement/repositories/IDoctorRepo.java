package com.geekster.HospitalManagement.repositories;

import com.geekster.HospitalManagement.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepo extends JpaRepository<Doctor,Long> {
    Doctor findByDoctorId(Long docId);
}
