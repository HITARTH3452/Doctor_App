package com.geekster.HospitalManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @EmbeddedId
    private AppointmentKey appointmentKey;

    @ManyToOne
    @JoinColumn(name = "fk_doctor_doc_Id")
    private Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;
}
