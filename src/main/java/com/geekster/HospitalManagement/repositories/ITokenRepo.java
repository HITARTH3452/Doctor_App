package com.geekster.HospitalManagement.repositories;

import com.geekster.HospitalManagement.models.AuthenticationToken;
import com.geekster.HospitalManagement.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findByPatient(Patient patient);
    AuthenticationToken findFirstByToken(String token);
}
