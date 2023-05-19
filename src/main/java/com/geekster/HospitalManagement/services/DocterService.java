package com.geekster.HospitalManagement.services;

import com.geekster.HospitalManagement.models.Appointment;
import com.geekster.HospitalManagement.models.Doctor;
import com.geekster.HospitalManagement.repositories.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocterService {

    @Autowired
    IDoctorRepo doctorRepo;
    public void addDoc(Doctor doc) {
        doctorRepo.save(doc);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> allDoctors = doctorRepo.findAll();
        return allDoctors;
    }

    public List<Appointment> getMyAppointments(Long docId) {

        Doctor myDoc = doctorRepo.findByDoctorId(docId);

        if(myDoc == null)
        {
            throw new IllegalStateException("The doctor does not exist");
        }

        return myDoc.getAppointments();
    }
}
