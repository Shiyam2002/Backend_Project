package com.appointmentScheduling.MasterTable;

import lombok.Data;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Component
@Data
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "countryID")
    private Integer countryID;

    @Column(name = "country_name")
    private String countryName;
}
