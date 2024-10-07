package com.appointmentScheduling.Entity;

import jakarta.persistence.Entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Builder
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor

@NamedQuery(name = "findAllByOrderByCreatedAtDesc", query = "SELECT p FROM Patient p ORDER BY p.createdAT DESC")
@NamedQuery(name = "joinNamedQuery", query = "SELECT p FROM Patient p JOIN p.address a")

@Entity
@Table(name = "Patient_Details")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Patient_ID")
    private Integer patientId;

    @Column(name = "Patient_Name", length = 50, nullable = false)
    private String patientName;

    @Column(name = "Password", length = 20, nullable = false)
    private String password;

    @Column(name = "Patient_Email", length = 40, nullable = false)
    private String patientEmail;

    @Column(name = "Patient_Phone", nullable = false)
    private long patientPhone;

    @Column(name = "Patient_DOB", nullable = false)
    private LocalDate patientDob;

    @Column(name = "Employment_Status", length = 20, nullable = false)
    private String employmentStatus;

    @Column(name = "Annual_Income", nullable = false)
    private Integer annualIncome;

    @CreationTimestamp
    @Column(name = "createdAT")
    private Timestamp createdAT;

    @UpdateTimestamp
    @Column(name = "updatedAT")
    private Timestamp updatedAT;

    // Mapping the foreign keys
    @OneToOne()
    @JoinColumn(name = "addressID", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne()
    @JoinColumn(name = "insurance_id", referencedColumnName = "insurance_id")
    private Insurance insurance;
}
