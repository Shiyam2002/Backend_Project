package com.appointmentScheduling.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import com.appointmentScheduling.MasterTable.City;
import com.appointmentScheduling.MasterTable.Country;
import com.appointmentScheduling.MasterTable.State;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressID;

    @Column(name = "doorNO")
    private String patientDoor;

    @Column(name = "street")
    private String patientStreet;

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "cityID")
    private City city;

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "stateID")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "countryID")
    private Country country;
}
