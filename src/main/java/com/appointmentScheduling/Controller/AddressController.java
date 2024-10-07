package com.appointmentScheduling.Controller;

import com.appointmentScheduling.DTO.AddressDTO;
import com.appointmentScheduling.Response.ResponseHandler;
import com.appointmentScheduling.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<Object> saveAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseHandler.getResponse("Address Created successfully", HttpStatus.CREATED,addressService.saveAddress(addressDTO));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> fetchAddressById(@PathVariable Integer id){
        return ResponseHandler.getResponse("Address fetched successfully", HttpStatus.OK, addressService.fetchAddressById(id));
    }

    @GetMapping("/addressList")
    public ResponseEntity<Object> fetchAllAddress(){
        return ResponseHandler.getResponse("All Addresses fetched successfully", HttpStatus.OK, addressService.fetchAllAddress());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAddressById(@PathVariable Integer id){
        return ResponseHandler.getResponse("Deleted Address Successfully", HttpStatus.OK, addressService.deleteAddressById(id));
    }
}
