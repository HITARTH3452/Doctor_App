package com.geekster.HospitalManagement.repositories;

import com.geekster.HospitalManagement.dto.SignUpInput;
import com.geekster.HospitalManagement.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient,Long> {
    Patient findFirstByPatientEmail(String email);
}
