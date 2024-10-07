package com.appointmentScheduling.MasterTable;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Component
@RequiredArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @Column(name = "cityID")
    private Integer cityID;

    @Column(name = "city_name")
    private String cityName;
}
