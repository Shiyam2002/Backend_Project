package com.appointmentScheduling.Repository;

import com.appointmentScheduling.MasterTable.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRespository extends JpaRepository<City, Integer> {
}
