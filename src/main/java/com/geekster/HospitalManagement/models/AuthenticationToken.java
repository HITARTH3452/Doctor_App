package com.geekster.HospitalManagement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;
    
    @OneToOne
    @JoinColumn(nullable = false, name = "fk_patientId")
    private Patient patient;

    public AuthenticationToken(Patient patient) {
        this.token = UUID.randomUUID().toString();
        this.tokenCreationDate = LocalDate.now();
        this.patient = patient;
    }
}
