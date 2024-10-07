package com.appointmentScheduling.Service;

import com.appointmentScheduling.DTO.AddressDTO;
import com.appointmentScheduling.DTO.InsuranceDTO;
import com.appointmentScheduling.DTO.PatientDTO;
import com.appointmentScheduling.Entity.Address;
import com.appointmentScheduling.Entity.Insurance;
import com.appointmentScheduling.Exception.PatientAlreadyExistsException;
import com.appointmentScheduling.Exception.PatientException;
import com.appointmentScheduling.Exception.PatientNotFoundException;
import com.appointmentScheduling.ProjectionRepository.PatientCountByEmploymentType;
import com.appointmentScheduling.ProjectionRepository.PatientNameMailProjection;
import com.appointmentScheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appointmentScheduling.BusinessLogic.PatientBO;
import com.appointmentScheduling.Entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientBO patientBO;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private InsuranceRespository insuranceRespository;

    @Autowired
    private CityRespository cityRespository;

    @Autowired
    private StateRepository stateRespository;

    @Autowired
    private CountryRepository countryRepository;

    public PatientDTO addPatient(PatientDTO patientDTO) throws PatientException, PatientAlreadyExistsException {
        Address address = saveAddress(patientDTO.getAddress());
        Insurance insurance = saveInsurance(patientDTO.getInsurance());
        Patient patient = PatientDTO.toPatientEntity(patientDTO, address, insurance);
        return PatientDTO.toPatientDTO(patientBO.addPatient(patient),
                AddressDTO.toAddressDTO(address),
                InsuranceDTO.toInsuranceDTO(insurance));
    }

    public PatientDTO updatePatient(PatientDTO patientDTO, Integer id) throws PatientException, PatientNotFoundException {
        Address address = saveAddress(patientDTO.getAddress());
        Insurance insurance = saveInsurance(patientDTO.getInsurance());
        Patient patient = PatientDTO.toPatientEntity(patientDTO, address, insurance);
        return PatientDTO.toPatientDTO(patientBO.updatePatient(patient, id),
                AddressDTO.toAddressDTO(address),
                InsuranceDTO.toInsuranceDTO(insurance));
    }

    public List<PatientDTO> deletePatientById(Integer patientId) throws PatientException, PatientNotFoundException {
        return patientBO.deletePatientById(patientId).stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public PatientDTO fetchByPatientId(Integer id) throws PatientException, PatientNotFoundException {
        PatientDTO patientDTO = patientBO.fetchByPatientId(id);
        return patientDTO;
    }

    public List<PatientDTO> fetchAllPatients() throws PatientException {
        return patientBO.fetchAllPatients().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }


    public List<PatientDTO> gettestPatients() throws PatientException {
        return patientBO.gettestPatients();
    }

    public List<PatientDTO> findAllPatientLeftJoinInsurance() throws PatientException {
        return patientBO.findAllPatientLeftJoinInsurance().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public List<PatientDTO> findAllPatientRightJoinInsurance() throws PatientException {
        return patientBO.findAllPatientRightJoinInsurance().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public List<PatientDTO> findAllPatientInnerJoinInsurance() throws PatientException {
        return patientBO.findAllPatientInnerJoinInsurance().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public List<PatientDTO> findAllPatientJoinInsurance() throws PatientException {
        return patientBO.findAllPatientJoinInsurance().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public List<PatientDTO> findAllByOrderByCreatedAtDesc() throws PatientException {
        return patientBO.findAllByOrderByCreatedAtDesc().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public List<PatientCountByEmploymentType> countEmploymentType() throws PatientException {
        return patientBO.countEmploymentType();
    }

    public List<PatientNameMailProjection> findPatientNamesAndEmailsOrdered() throws PatientException {
        return patientBO.findPatientNamesAndEmailsOrdered();
    }

    public List<PatientDTO> joinNamedQuery() throws PatientException {

        return patientBO.joinNamedQuery().stream()
                .map(this::convertToPatientDTO)
                .toList();
    }

    public Long countTotalPatients() throws PatientException {
        return patientBO.countTotalPatients();
    }

    public com.appointmentScheduling.Entity.Address saveAddress(AddressDTO addressDTO) {
        com.appointmentScheduling.Entity.Address address = AddressDTO.toAddressEntity(addressDTO, cityRespository, stateRespository, countryRepository);
        return addressRepository.save(address);
    }

    public Insurance saveInsurance(InsuranceDTO insuranceDTO) {
        Insurance insurance = InsuranceDTO.toInsuranceEntity(insuranceDTO);
        return insuranceRespository.save(insurance);
    }

    public PatientDTO convertToPatientDTO(Patient patient) {
        AddressDTO addressDTO = AddressDTO.toAddressDTO(patient.getAddress());
        InsuranceDTO insuranceDTO = (patient.getInsurance() != null)
                ? InsuranceDTO.toInsuranceDTO(patient.getInsurance())
                : null;
        return PatientDTO.toPatientDTO(patient, addressDTO, insuranceDTO);
    }
}
