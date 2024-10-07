package com.appointmentScheduling.DTO;

import com.appointmentScheduling.Entity.Insurance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDTO {

    private String insuranceID;

    private String insuranceProvider;

    private String policyNumber;

    private String coverageDetails;

    public static InsuranceDTO toInsuranceDTO(Insurance insurance)
    {
        return InsuranceDTO.builder()
                .insuranceID(insurance.getInsuranceID())
                .insuranceProvider(insurance.getInsuranceProvider())
                .policyNumber(insurance.getPolicyNumber())
                .coverageDetails(insurance.getCoverageDetails())
                .build();
    }

    public static Insurance toInsuranceEntity(InsuranceDTO insuranceDTO)
    {
        return Insurance.builder()
                .insuranceID(insuranceDTO.getInsuranceID())
                .insuranceProvider(insuranceDTO.getInsuranceProvider())
                .policyNumber(insuranceDTO.getPolicyNumber())
                .coverageDetails(insuranceDTO.getCoverageDetails())
                .build();
    }
}
