package com.appointmentScheduling.Repository;

import com.appointmentScheduling.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRespository extends JpaRepository<Insurance,String> {
}
