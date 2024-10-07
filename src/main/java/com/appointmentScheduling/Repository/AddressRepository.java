package com.appointmentScheduling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointmentScheduling.Entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {


}
