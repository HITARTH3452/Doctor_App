package com.geekster.HospitalManagement.controllers;

import com.geekster.HospitalManagement.models.Appointment;
import com.geekster.HospitalManagement.models.Doctor;
import com.geekster.HospitalManagement.services.DocterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DocterService docterService;

    @PostMapping()
    void addDoctros(@RequestBody Doctor doc) {
        docterService.addDoc(doc);
    }

    @GetMapping("{docId}/appointments")
    ResponseEntity<List<Appointment>> getDocMyAppointments(@PathVariable Long docId){
        List<Appointment> myAppointments = null;
        HttpStatus status;
        try
        {
            myAppointments = docterService.getMyAppointments(docId);
            //System.out.println(myAppointments);
            if(myAppointments.isEmpty())
            {
                status = HttpStatus.NO_CONTENT;
            }
            else
            {
                status = HttpStatus.OK;
            }
        }
        catch(Exception e)
        {
            System.out.println("The doc Id is not valid");
            status = HttpStatus.BAD_REQUEST;

        }

        return new ResponseEntity<List<Appointment>>(myAppointments,status);


    }


}
