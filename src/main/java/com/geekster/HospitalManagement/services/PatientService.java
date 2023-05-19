package com.geekster.HospitalManagement.services;

import com.geekster.HospitalManagement.dto.SignInInput;
import com.geekster.HospitalManagement.dto.SignInOutput;
import com.geekster.HospitalManagement.dto.SignUpInput;
import com.geekster.HospitalManagement.dto.SignUpOutput;
import com.geekster.HospitalManagement.models.AppointmentKey;
import com.geekster.HospitalManagement.models.AuthenticationToken;
import com.geekster.HospitalManagement.models.Doctor;
import com.geekster.HospitalManagement.models.Patient;
import com.geekster.HospitalManagement.repositories.IDoctorRepo;
import com.geekster.HospitalManagement.repositories.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    DocterService docterService;

    @Autowired
    AppointmentService appointmentService;



    public SignUpOutput signUp(SignUpInput signUpDto) {

        // user present or not
        Patient patient = patientRepo.findFirstByPatientEmail(signUpDto.getPatientEmail());

        if(patient != null){
            throw new IllegalStateException("Patient already exists!!!!....sign in instead");
        }
        
        // encryption
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpDto.getPatientPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //save the user

        patient = new Patient(signUpDto.getPatientFirstName(),signUpDto.getPatientLastName(),signUpDto.getPatientEmail(),encryptedPassword,signUpDto.getPatientContact());

        patientRepo.save(patient);

        AuthenticationToken token = new AuthenticationToken(patient);

        tokenService.saveToken(token);

        //token creation -> present in authentication service

        return new SignUpOutput("patient registered","Patient created");


    }
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        return new BigInteger(1,md5.digest()).toString(16);
    }

    public SignInOutput signIn(SignInInput signInDto) {

        //get email

        Patient patient = patientRepo.findFirstByPatientEmail(signInDto.getPatientEmail());

        if(patient == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        //encrypt the password

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPatientPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(patient.getPatientPassword());
        if(!isPasswordValid)

        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        //figure out the token

        AuthenticationToken authToken = tokenService.getToken(patient);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!",authToken.getToken());

    }

    public List<Doctor> getAllDoctors() {
       return docterService.getAllDoctors();
    }

    public void cancelAppointment(AppointmentKey key) {

        appointmentService.cancelAppointment(key);

    }
}
