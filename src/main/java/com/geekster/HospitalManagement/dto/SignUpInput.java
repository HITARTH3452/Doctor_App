package com.geekster.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {

    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientPassword;
    private String patientContact;
}
