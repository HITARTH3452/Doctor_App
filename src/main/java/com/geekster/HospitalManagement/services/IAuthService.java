package com.geekster.HospitalManagement.services;

import com.geekster.HospitalManagement.models.AuthenticationToken;
import com.geekster.HospitalManagement.models.Patient;


public interface IAuthService {

    void saveToken(AuthenticationToken token);
    AuthenticationToken getToken(Patient patient);
    boolean authenticate(String userEmail, String token);
}
