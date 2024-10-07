package com.appointmentScheduling.BusinessLogic;

import com.appointmentScheduling.DTO.AddressDTO;
import com.appointmentScheduling.Entity.Address;
import com.appointmentScheduling.Repository.AddressRepository;
import com.appointmentScheduling.Repository.CityRespository;
import com.appointmentScheduling.Repository.CountryRepository;
import com.appointmentScheduling.Repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class AddressBO {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRespository cityRespository;

    @Autowired
    private StateRepository stateRespository;

    @Autowired
    private CountryRepository countryRespository;

    // Add methods for CRUD operations on AddressEntity

    public Address saveAddress(AddressDTO addressDTO) {
        Address address = AddressDTO.toAddressEntity(addressDTO, cityRespository, stateRespository, countryRespository);
        return addressRepository.save(address);
    }

    public Address fetchAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> fetchAllAddress(){
        return addressRepository.findAll();
    }

    public List<Address> deleteAddressById(Integer id){
        addressRepository.deleteById(id);
        return fetchAllAddress();
    }
}
