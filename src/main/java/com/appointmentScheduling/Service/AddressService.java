package com.appointmentScheduling.Service;


import com.appointmentScheduling.BusinessLogic.AddressBO;
import com.appointmentScheduling.DTO.AddressDTO;
import com.appointmentScheduling.Entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressBO addressBO;

    public AddressDTO saveAddress(AddressDTO addressDTO) {
       return AddressDTO.toAddressDTO(addressBO.saveAddress(addressDTO));
    }

    public AddressDTO fetchAddressById(Integer id) {
        return AddressDTO.toAddressDTO(addressBO.fetchAddressById(id));
    }

    public List<AddressDTO> fetchAllAddress(){
        return addressBO.fetchAllAddress().stream()
                .map(AddressDTO::toAddressDTO)
                .toList();
    }

    public List<AddressDTO> deleteAddressById(Integer id){
        return addressBO.deleteAddressById(id).stream()
                .map(AddressDTO::toAddressDTO)
                .toList();
    }
}
