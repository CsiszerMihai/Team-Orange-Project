package com.orange.team.services;

import com.orange.team.models.dtos.CustomerRegistrationDTO;

import java.util.List;

public interface CustomerRegistrationService {

    CustomerRegistrationDTO createCustomerUser (CustomerRegistrationDTO customerRegistrationDTO);

    List<CustomerRegistrationDTO> getCustomerList();

    CustomerRegistrationDTO updateCustomerListById(Long id, CustomerRegistrationDTO customerRegistrationDTO);

    void deleteCustomerById(Long id);
}
