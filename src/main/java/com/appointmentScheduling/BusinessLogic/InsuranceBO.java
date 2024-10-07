package com.appointmentScheduling.BusinessLogic;

import com.appointmentScheduling.DTO.InsuranceDTO;
import com.appointmentScheduling.Entity.Insurance;
import com.appointmentScheduling.Repository.InsuranceRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceBO {

    @Autowired
    private InsuranceRespository insuranceRespository;

    public InsuranceDTO saveInsurance(InsuranceDTO insuranceDTO) {
        Insurance insurance = InsuranceDTO.toInsuranceEntity(insuranceDTO);
        return InsuranceDTO.toInsuranceDTO(insuranceRespository.save(insurance));
    }

    public InsuranceDTO fetchInsuranceID(String insuranceId) {
        Insurance insurance = insuranceRespository.getById(insuranceId);
        return InsuranceDTO.toInsuranceDTO(insurance);
    }

    public List<Insurance> fetchAllInsurance() {
        return insuranceRespository.findAll();
    }

    public List<Insurance> deleteInsuranceById(String id) {
        insuranceRespository.deleteById(id);
        return insuranceRespository.findAll();
    }
}
