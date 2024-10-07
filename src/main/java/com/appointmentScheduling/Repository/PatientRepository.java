package com.appointmentScheduling.Repository;

import com.appointmentScheduling.ProjectionRepository.PatientCountByEmploymentType;
import com.appointmentScheduling.ProjectionRepository.PatientNameMailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appointmentScheduling.Entity.Patient;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, CrudRepository<Patient, Integer> {
    // Retrieve All Patient Data
    @Query("SELECT p FROM Patient p WHERE p.patientId =10")
    Patient findPatient();

    // Left Join of Patient
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.insurance i")
    List<Patient> findAllPatientLeftJoinInsurance();

    // Right Join of Patient
    @Query("Select p FROM Patient p RIGHT JOIN p.insurance i")
    List<Patient> findAllPatientRightJoinInsurance();

    // Inner Join of Patient
    @Query("Select p FROM Patient p INNER JOIN FETCH p.insurance i")
    List<Patient> findAllPatientInnerJoinInsurance();

    @Query("Select p FROM Patient p JOIN FETCH p.insurance i")
    List<Patient> findAllPatientJoinInsurance();

    // Order By for Patient Name
    @Query("SELECT p.patientName AS patientName, p.patientEmail AS patientEmail FROM Patient p ORDER BY p.patientName ASC")
    List<PatientNameMailProjection> findPatientNamesAndEmailsOrdered();

    // Group By for Patient Employment Type
    @Query("SELECT p.employmentStatus AS employmentStatus, COUNT(p) AS employeeCount FROM Patient p GROUP BY p.employmentStatus")
    List<PatientCountByEmploymentType> countEmploymentType();

    // Count of Total Patients
    @Query("SELECT count(patientId) FROM Patient")
    Long countTotalPatients();

    // Named query 01 Order By using Creation of patient details
    @Query(name = "findAllByOrderByCreatedAtDesc")
    List<Patient> findAllByOrderByCreatedAtDesc();

    // Named query 02 Joining Patient and Address tables
    @Query(name = "joinNamedQuery")
    List<Patient> joinNamedQuery();

    @Query("SELECT p FROM Patient p WHERE p.patientEmail = :email OR p.patientPhone = :phone")
    Optional<Patient> findByEmailOrPhone(@Param("email") String email, @Param("phone") long phone);

}
