package com.appointmentScheduling.DTO;

import com.appointmentScheduling.Entity.Address;
import com.appointmentScheduling.Repository.CityRespository;
import com.appointmentScheduling.Repository.CountryRepository;
import com.appointmentScheduling.Repository.StateRepository;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private String patientDoor;

    private String patientStreet;

    private Integer cityID;

    private Integer stateID;

    private Integer countryID;

    public static Address toAddressEntity(AddressDTO addressDTO, CityRespository cityRespository, StateRepository stateRespository, CountryRepository countryRepository) {
        return Address.builder().patientDoor(addressDTO.getPatientDoor())
                .patientStreet(addressDTO.getPatientStreet())
                .city(cityRespository.findById(addressDTO.getCityID()).orElse(null))
                .state(stateRespository.findById(addressDTO.getStateID()).orElse(null))
                .country(countryRepository.findById(addressDTO.getCountryID()).orElse(null))
                .build();
    }

    public static AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .patientDoor(address.getPatientDoor())
                .patientStreet(address.getPatientStreet())
                .cityID(address.getCity().getCityID())
                .stateID(address.getState().getStateID())
                .countryID(address.getCountry().getCountryID())
                .build();
    }

}
