package com.orange.team.controllers;


import com.orange.team.models.dtos.CustomerRegistrationDTO;
import com.orange.team.services.CustomerRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRegistrationController {

    private final CustomerRegistrationService customerRegistrationService;

    public CustomerRegistrationController(CustomerRegistrationService customerRegistrationService) {
        this.customerRegistrationService = customerRegistrationService;
    }

    @PostMapping("/api/customer_registration")
    public ResponseEntity<CustomerRegistrationDTO> createCustomerRegistrationUser(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        return ResponseEntity.ok(customerRegistrationService.createCustomerUser(customerRegistrationDTO));
    }

    @GetMapping("/api/customer_registration")
    public ResponseEntity<List<CustomerRegistrationDTO>> getCustomerUserList() {
        return ResponseEntity.ok(customerRegistrationService.getCustomerList());
    }

    @PutMapping("/api/customer_registration")
    public ResponseEntity<CustomerRegistrationDTO> updateCustomerUserById(Long id, CustomerRegistrationDTO customerRegistrationDTO) {
        return ResponseEntity.ok(customerRegistrationService.updateCustomerListById(id, customerRegistrationDTO));
    }

    @DeleteMapping("/api/customer_registration")
    public ResponseEntity<Void> deleteCustomerById(Long id){
        customerRegistrationService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}