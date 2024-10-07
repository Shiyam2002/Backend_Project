package com.appointmentScheduling.BusinessLogic;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.appointmentScheduling.DTO.AddressDTO;
import com.appointmentScheduling.DTO.InsuranceDTO;
import com.appointmentScheduling.DTO.PatientDTO;
import com.appointmentScheduling.Entity.Insurance;
import com.appointmentScheduling.Exception.PatientAlreadyExistsException;
import com.appointmentScheduling.Exception.PatientException;
import com.appointmentScheduling.Exception.PatientNotFoundException;
import com.appointmentScheduling.ProjectionRepository.PatientCountByEmploymentType;
import com.appointmentScheduling.ProjectionRepository.PatientNameMailProjection;
import com.appointmentScheduling.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appointmentScheduling.Entity.Address;
import com.appointmentScheduling.Entity.Patient;


@Component
public class PatientBO {

    @Autowired
    private PatientRepository patientRepository;

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

    @Transactional
    public Patient addPatient(Patient patient) throws PatientException, PatientAlreadyExistsException {
        isValidInputs(patient);
        Optional<Patient> existingPatient = patientRepository.findByEmailOrPhone(patient.getPatientEmail(), patient.getPatientPhone());

        if (existingPatient.isPresent()) {
            throw new PatientAlreadyExistsException("Patient with this email or phone number already exists.");
        }
        return patientRepository.save(patient);
    }

    // Update patient details
    public Patient updatePatient(Patient existingPatient, Integer id) throws PatientException, PatientNotFoundException {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new PatientNotFoundException("Patient not found with ID: " + id);
        }
        existingPatient.setPatientId(id);
        isValidInputs(existingPatient);

        // Save updated Patient
        Patient updatedPatient = patientRepository.save(existingPatient);
        return updatedPatient;
    }

    @Transactional
    public List<Patient> deletePatientById(Integer patientId) throws PatientException, PatientNotFoundException {
        if (fetchByPatientId(patientId) == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }
        Optional<Patient> patient = patientRepository.findById(patientId);
        Patient patientEntity = patient.get();
        addressRepository.deleteById(patientEntity.getAddress().getAddressID());
        insuranceRespository.deleteById(patientEntity.getInsurance().getInsuranceID());
        patientRepository.deleteById(patientId);
        return fetchAllPatients();
    }


    public PatientDTO fetchByPatientId(Integer patientId) throws PatientException, PatientNotFoundException {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (!optionalPatient.isPresent()) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }

        Patient patient = optionalPatient.get();

        AddressDTO addressDTO = AddressDTO.toAddressDTO(patient.getAddress());

        InsuranceDTO insuranceDTO = null;
        if (patient.getInsurance() != null) {
            insuranceDTO = InsuranceDTO.toInsuranceDTO(patient.getInsurance());
        }

        return PatientDTO.toPatientDTO(patient, addressDTO, insuranceDTO);
    }

    public List<Patient> fetchAllPatients() throws PatientException {
        return patientRepository.findAll();
    }

    public List<PatientDTO> gettestPatients() {
        Patient patient = patientRepository.findPatient();
        Address address = patient.getAddress();
        Insurance insurance = patient.getInsurance();

        AddressDTO addressDTO = AddressDTO.toAddressDTO(address);
        InsuranceDTO insuranceDTO = InsuranceDTO.toInsuranceDTO(insurance);

        PatientDTO testPatientDTO = PatientDTO.toPatientDTO(patient, addressDTO, insuranceDTO);
        return List.of(testPatientDTO);
    }

    public List<Patient> findAllPatientLeftJoinInsurance() throws PatientException {
        return patientRepository.findAllPatientLeftJoinInsurance();
    }

    public List<Patient> findAllPatientRightJoinInsurance() throws PatientException {
        return patientRepository.findAllPatientRightJoinInsurance();
    }

    public List<Patient> findAllPatientInnerJoinInsurance() throws PatientException {
        return patientRepository.findAllPatientInnerJoinInsurance();
    }

    public List<Patient> findAllPatientJoinInsurance() throws PatientException {
        return patientRepository.findAllPatientJoinInsurance();
    }

    public List<Patient> findAllByOrderByCreatedAtDesc() throws PatientException {
        return patientRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<PatientCountByEmploymentType> countEmploymentType() throws PatientException {
        return patientRepository.countEmploymentType();
    }

    public List<PatientNameMailProjection> findPatientNamesAndEmailsOrdered() throws PatientException {
        return patientRepository.findPatientNamesAndEmailsOrdered();
    }

    public List<Patient> joinNamedQuery() throws PatientException {
        return patientRepository.joinNamedQuery();
    }


    public Long countTotalPatients() throws PatientException {
        return patientRepository.countTotalPatients();
    }


    private void isValidInputs(Patient patient) throws PatientException {
        if (patient.getPatientName() == null) {
            throw new PatientException("Patient name is mandatory");
        }
        if (patient.getPassword() == null) {
            throw new PatientException("Password is mandatory");
        }
        if (patient.getPatientEmail() == null) {
            throw new PatientException("Patient email is mandatory");
        }
        if (patient.getPatientDob() == null) {
            throw new PatientException("Date of Birth is mandatory");
        }
        if (patient.getEmploymentStatus() == null) {
            throw new PatientException("Employment Status is mandatory");
        }
        if (patient.getAnnualIncome() == null) {
            throw new PatientException("Annual Income is mandatory");
        }
        if (!isValidName(patient.getEmploymentStatus())) {
            throw new PatientException("Invalid Employment Status and should contain only alphabets");
        }

        if (!isValidName(patient.getPatientName())) {
            throw new PatientException("Patient name is mandatory and should contain only alphabets");
        }
        if (!isValidEmail(patient.getPatientEmail())) {
            throw new PatientException("Invalid email format");
        }
        if (!isvalidPhoneNumbeer(patient.getPatientPhone())) {
            throw new PatientException("Invalid phone Number");
        }
        if (isValidDOB(patient.getPatientDob())) {
            throw new PatientException("Invalid Date of Birth can't be future");
        }
    }

    // Email validation regex pattern
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Password validation regex pattern
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()])(?=\\S+$).{8,20}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    // Phone validation regex pattern
    private static final String PHONE_REGEX = "^\\d{10}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    // Method to check if the phone number is valid using regex
    public boolean isvalidPhoneNumbeer(Long phone) {
        String phoneStr = phone.toString();
        return PHONE_PATTERN.matcher(phoneStr).matches();
    }

    // Method to check if a string contains only numeric values
    public boolean isNumeric(String data) {
        for (int index = 0; index < data.length(); index++) {
            Character digit = data.charAt(index);
            if (!Character.isDigit(digit)) {
                return false;
            }
        }
        return true;
    }

    // Method to validate that the date of birth is not a future date
    public boolean isValidDOB(LocalDate dob) {
        return dob.isAfter(LocalDate.now());
    }

    // Method to validate the name: it should only contain letters and spaces, and should not be null or empty
    public boolean isValidName(String name) {
        if (name.length() <= 1) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ')
                return false;
        }
        return true;
    }

    // Method to validate email format using the regex pattern
    public boolean isValidEmail(String email) {
        boolean isValid = EMAIL_PATTERN.matcher(email).matches();
        return isValid;
    }

    // Method to validate if a password is strong based on the regex pattern
    public boolean isPasswordStrong(String password) {
        boolean isStrong = PASSWORD_PATTERN.matcher(password).matches();
        return isStrong;
    }


}
