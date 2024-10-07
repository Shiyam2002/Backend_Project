package com.appointmentScheduling.Service;

import com.appointmentScheduling.BusinessLogic.InsuranceBO;
import com.appointmentScheduling.DTO.InsuranceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceBO insuranceBO;

    public InsuranceDTO saveInsurance(InsuranceDTO insuranceDTO)
    {
        return insuranceBO.saveInsurance(insuranceDTO);
    }

    public InsuranceDTO fetchInsuranceById(String insuranceId)
    {
        return insuranceBO.fetchInsuranceID(insuranceId);
    }

    public List<InsuranceDTO> fetchAllInsurance(){
        return insuranceBO.fetchAllInsurance().stream()
                .map(InsuranceDTO::toInsuranceDTO)
                .toList();
    }

    public List<InsuranceDTO> deleteInsuranceById(String id){
        return insuranceBO.deleteInsuranceById(id).stream()
                .map(InsuranceDTO::toInsuranceDTO)
                .toList();
    }


}
