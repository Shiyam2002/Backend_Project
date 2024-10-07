package com.appointmentScheduling.Controller;

import com.appointmentScheduling.DTO.InsuranceDTO;
import com.appointmentScheduling.Response.ResponseHandler;
import com.appointmentScheduling.Service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveInsurance(InsuranceDTO insuranceDTO) {
        return ResponseHandler.getResponse("Insurance saved successfully", HttpStatus.CREATED, insuranceService.saveInsurance(insuranceDTO));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> fetchInsuranceById(@PathVariable String id) {
        return ResponseHandler.getResponse("Insurance Details fetched successfully", HttpStatus.ACCEPTED, insuranceService.fetchInsuranceById(id));
    }

    @GetMapping("/insuranceList")
    public ResponseEntity<Object> fetchAllInsurance(){
        return ResponseHandler.getResponse("Insurance Details fetched successfully", HttpStatus.OK, insuranceService.fetchAllInsurance());
    }

    @DeleteMapping("/Delete")
    public ResponseEntity<Object> deleteInsuranceById(@PathVariable String id){
        return ResponseHandler.getResponse("Insurance Details Delete", HttpStatus.OK, insuranceService.deleteInsuranceById(id));
    }
}
