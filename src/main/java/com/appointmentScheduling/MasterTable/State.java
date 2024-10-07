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
@RequiredArgsConstructor
@Component
@Table(name = "state")
public class State {

    @Id
    @Column(name = "stateID")
    private Integer stateID;

    @Column(name = "state_name")
    private String stateName;
}
