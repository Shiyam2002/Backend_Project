package com.appointmentScheduling.DTO;

import com.appointmentScheduling.Entity.Address;
import com.appointmentScheduling.Entity.Insurance;
import com.appointmentScheduling.Entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class PatientDTO {

    private Integer patientID;

    private String patientName;

    private String patientEmail;

    private long patientPhone;

    private LocalDate patientDob;

    private String employmentStatus;

    private Integer annualIncome;

    private AddressDTO address;

    private InsuranceDTO insurance;

    public static PatientDTO toPatientDTO(Patient patient, AddressDTO addressDTO, InsuranceDTO insuranceDTO) {

        return PatientDTO.builder()
                .patientID(patient.getPatientId())
                .patientName(patient.getPatientName())
                .patientEmail(patient.getPatientEmail())
                .patientDob(patient.getPatientDob())
                .annualIncome(patient.getAnnualIncome())
                .patientPhone(patient.getPatientPhone())
                .employmentStatus(patient.getEmploymentStatus())
                .address(addressDTO)
                .insurance(insuranceDTO).build();

    }

    public static Patient toPatientEntity(PatientDTO patientDTO, Address address, Insurance insurance) {
        return Patient.builder()
                .patientId(patientDTO.patientID)
                .patientName(patientDTO.patientName)
                .patientEmail(patientDTO.patientEmail)
                .patientDob(patientDTO.getPatientDob())
                .annualIncome(patientDTO.getAnnualIncome())
                .patientPhone(patientDTO.getPatientPhone())
                .employmentStatus(patientDTO.getEmploymentStatus())
                .address(address)
                .insurance(insurance)
                .build();
    }


}
