package com.geekster.HospitalManagement.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

@Embeddable
public class AppointmentKey {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long appId;
    public LocalDateTime time;
}
