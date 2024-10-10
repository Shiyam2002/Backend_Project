package com.appointmentScheduling.Controller;


import com.appointmentScheduling.DTO.PatientDTO;
import com.appointmentScheduling.Exception.PatientAlreadyExistsException;
import com.appointmentScheduling.Exception.PatientException;
import com.appointmentScheduling.Exception.PatientNotFoundException;
import com.appointmentScheduling.ProjectionRepository.PatientNameMailProjection;
import com.appointmentScheduling.Repository.PatientRepository;
import com.appointmentScheduling.Response.ResponseHandler;
import com.appointmentScheduling.Service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {


    @Autowired
    private PatientRepository patient;

    @Autowired
    private PatientService service;

    static Logger log = Logger.getLogger(PatientController.class);

    @PostMapping("/signup")
    public ResponseEntity<Object> addPatient(@RequestBody PatientDTO patientRequest) throws PatientException, PatientAlreadyExistsException {
        if (log.isInfoEnabled()) {
            log.info("Trying to add Patient's details : " + patientRequest);
        }
        PatientDTO patientDTO = service.addPatient(patientRequest);
        log.info("Patient Added Successfully");
        return ResponseHandler.getResponse("Patient added successfully", HttpStatus.CREATED, patientDTO);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Object> updatePatient(@RequestBody PatientDTO patientDTO, @PathVariable Integer id) throws PatientException, PatientNotFoundException {
        return ResponseHandler.getResponse("Patient Updated Successfully", HttpStatus.OK, service.updatePatient(patientDTO, id));
    }

    @DeleteMapping("/deletePatient/{patientId}")
    public ResponseEntity<Object> deletePatientById(@PathVariable Integer patientId) throws PatientException, PatientNotFoundException {
        return ResponseHandler.getResponse("Patient deleted successfully", HttpStatus.OK, service.deletePatientById(patientId));
    }

    @GetMapping("/id/{patientId}")
    public ResponseEntity<Object> fetchByPatientId(@PathVariable Integer patientId) throws PatientException, PatientNotFoundException {
        if (log.isDebugEnabled()) {
            log.debug("Fetching patient by ID: " + patientId);
        }
        PatientDTO patientDTO = service.fetchByPatientId(patientId);
        if (log.isInfoEnabled()) {
            log.info("Patient found: " + patientDTO);
        }
        return ResponseHandler.getResponse("Patient fetched successfully", HttpStatus.ACCEPTED, patientDTO);
    }

    @GetMapping("/patientList")
    public ResponseEntity<Object> fetchAllPatients() throws PatientException {
        log.info("Fetching all patient");
        List<PatientDTO> patientList = service.fetchAllPatients();
        if (log.isInfoEnabled()) {
            log.info("Total patient retrieved: " + patientList.size() + " Details of the patients fetched: " + patientList);
        }
        return ResponseHandler.getResponse("All Patient Details fetched successfully", HttpStatus.CREATED, patientList);
    }

    @GetMapping("/FetchPatient")
    public ResponseEntity<Object> getTestPatient() throws PatientException, PatientNotFoundException {
        //List<PatientDTO> patientDTOS = service.gettestPatients();
        return ResponseHandler.getResponse("Patient fetched Successfully", HttpStatus.OK, service.gettestPatients());
    }

    @GetMapping("/LeftJoin")
    public ResponseEntity<Object> findAllPatientLeftJoinInsurance() throws PatientException {
        log.info("Fetching all patient left join on Insurance");
        return ResponseHandler.getResponse("Patient fetched Successfully", HttpStatus.OK, service.findAllPatientLeftJoinInsurance());
    }

    @GetMapping("/RightJoin")
    public ResponseEntity<Object> findAllPatientRightJoinInsurance() throws PatientException {
        log.info("Fetching all patient left join on Insurance");
        return ResponseHandler.getResponse("Patient fetched Successfully", HttpStatus.OK, service.findAllPatientRightJoinInsurance());
    }

    @GetMapping("/InnerJoin")
    public ResponseEntity<Object> findAllPatientInnerJoinInsurance() throws PatientException {
        log.info("Fetching all patient cross join on Insurance");
        return ResponseHandler.getResponse("Patient cross join fetched Successfully", HttpStatus.OK, service.findAllPatientInnerJoinInsurance());
    }

    @GetMapping("/Join")
    public ResponseEntity<Object> findAllPatientJoinInsurance() throws PatientException {
        log.info("Fetching all patient cross join on Insurance" + service.findAllPatientJoinInsurance());
        return ResponseHandler.getResponse("Patient cross join fetched Successfully", HttpStatus.OK, service.findAllPatientJoinInsurance());
    }

    @GetMapping("/NameQuery01")
    public ResponseEntity<Object> findAllByOrderByCreatedAtDesc() throws PatientException {
        log.debug("Fetching all patients by ordering by createdAt");
        return ResponseHandler.getResponse("Patient fetched Successfully", HttpStatus.OK, service.findAllByOrderByCreatedAtDesc());
    }

    @GetMapping("/NameQuery02")
    public ResponseEntity<Object> joinNamedQuery() throws PatientException {

        log.debug("Fetching all patients by joining with named query");
        return ResponseHandler.getResponse("JoinNamedQuery", HttpStatus.OK, service.joinNamedQuery());
    }

    @GetMapping("/PatientOrderBy")
    public ResponseEntity<Object> findPatientNamesAndEmailsOrdered() throws PatientException {
        List<PatientNameMailProjection> result = service.findPatientNamesAndEmailsOrdered();
        log.debug("Fetching all patients by ordering by name and email" + service.findPatientNamesAndEmailsOrdered());
        return ResponseHandler.getResponse("Patient fetched Successfully", HttpStatus.OK, result);
    }

    @GetMapping("/CountEmployee")
    public ResponseEntity<Object> countEmploymentType() throws PatientException {
        log.debug("Fetching count of patients by employment type");
        return ResponseHandler.getResponse("Count of Employment type fetched successfully", HttpStatus.OK, service.countEmploymentType());
    }

    @GetMapping("/Count")
    public ResponseEntity<Object> countTotalPatients() throws PatientException   {
        log.debug("Fetching total number of patients");
        return ResponseHandler.getResponse("Total number of patients fetched", HttpStatus.OK, service.countTotalPatients());
    }
}
