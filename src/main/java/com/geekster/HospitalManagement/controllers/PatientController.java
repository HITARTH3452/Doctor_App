package com.geekster.HospitalManagement.controllers;

import com.geekster.HospitalManagement.dto.SignInInput;
import com.geekster.HospitalManagement.dto.SignInOutput;
import com.geekster.HospitalManagement.dto.SignUpInput;
import com.geekster.HospitalManagement.dto.SignUpOutput;
import com.geekster.HospitalManagement.models.AppointmentKey;
import com.geekster.HospitalManagement.models.Doctor;
import com.geekster.HospitalManagement.models.Patient;
import com.geekster.HospitalManagement.repositories.IPatientRepo;
import com.geekster.HospitalManagement.services.AuthenticationService;
import com.geekster.HospitalManagement.services.IAuthService;
import com.geekster.HospitalManagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController{

    @Autowired
    PatientService patientService;

    @Autowired
    IAuthService authService;

    @Autowired
    IPatientRepo patientRepo;
    //sign Up

    @PostMapping("/signup")
    public SignUpOutput signup(@RequestBody SignUpInput signUpDto){
        return patientService.signUp(signUpDto);
            }

    //Sign in
    @PostMapping("/signin")
    public SignInOutput signin(@RequestBody SignInInput signInDto){
        return patientService.signIn(signInDto);
    }

    @GetMapping("doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam String userEmail , @RequestParam String token){
        HttpStatus status;
        List<Doctor> allDoctors = null;
        //authenticate

        //token : calculate token -> find email in Db corr to this token-> match the emails
        if(authService.authenticate(userEmail,token))
        {
            allDoctors =  patientService.getAllDoctors();
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<List<Doctor>>(allDoctors, status);
    }

    //todo : move the create appointment in Appointment controller in here along with authentication...!

    //delete my appointment :

    @DeleteMapping("appointment")
    ResponseEntity<Void> cancelAppointment(@RequestParam String userEmail, @RequestParam String token, @RequestBody AppointmentKey key)
    {

        HttpStatus status;
        if(authService.authenticate(userEmail,token))
        {
            patientService.cancelAppointment(key);
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<Void>(status);

    }
}
