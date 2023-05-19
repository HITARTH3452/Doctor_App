package com.geekster.HospitalManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data

@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    @Column(unique = true)
    private String patientEmail;
    @Column()
    private String patientPassword;
    private String patientContact;

    public Patient(String patientFirstName, String patientLastName, String patientEmail, String patientPassword, String patientContact) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
        this.patientContact = patientContact;
    }

   // @OneToOne(mappedBy = "patient")
    //private Appointment appointment;
}
