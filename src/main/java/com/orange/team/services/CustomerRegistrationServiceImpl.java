package com.orange.team.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.team.exceptions.CustomerNotFoundException;
import com.orange.team.exceptions.TravelPackageNotFoundException;
import com.orange.team.models.dtos.CustomerRegistrationDTO;
import com.orange.team.models.dtos.TravelPackageDTO;
import com.orange.team.models.entities.CustomerRegistration;
import com.orange.team.models.entities.TravelPackage;
import com.orange.team.repositories.CustomerRegistrationRepository;
import com.orange.team.repositories.TravelPackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private static final Logger log = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final CustomerRegistrationRepository customerRegistrationRepository;


    public CustomerRegistrationServiceImpl(ObjectMapper objectMapper, CustomerRegistrationRepository customerRegistrationRepository) {
        this.objectMapper = objectMapper;
        this.customerRegistrationRepository = customerRegistrationRepository;
    }

    @Override
    public CustomerRegistrationDTO createCustomerUser(CustomerRegistrationDTO customerRegistrationDTO) {
        CustomerRegistration customerRegistrationEntity = objectMapper.convertValue(customerRegistrationDTO, CustomerRegistration.class);
        CustomerRegistration customerRegistrationEntityResponse = customerRegistrationRepository.save(customerRegistrationEntity);
        log.info("Customer user with id {} was saved.", customerRegistrationEntityResponse.getId());

        return objectMapper.convertValue(customerRegistrationEntityResponse, CustomerRegistrationDTO.class);
    }

    @Override
    public List<CustomerRegistrationDTO> getCustomerList() {
        List<CustomerRegistration> customerRegistrationList = customerRegistrationRepository.findAll();

        return customerRegistrationList.stream()
                .map(customerRegistration -> objectMapper.convertValue(customerRegistration, CustomerRegistrationDTO.class))
                .toList();
    }

    @Override
    public CustomerRegistrationDTO updateCustomerListById(Long id, CustomerRegistrationDTO customerRegistrationDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Customer Package ID cannot be null.");
        }
        CustomerRegistration existingCustomerRegistration = customerRegistrationRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException(id));

        updateCustomerRegistration(existingCustomerRegistration, customerRegistrationDTO);
        CustomerRegistration updatedCustomer = customerRegistrationRepository.save(existingCustomerRegistration);
        log.info("Customer user with id {} was updated", updatedCustomer.getId());

        return objectMapper.convertValue(updatedCustomer, CustomerRegistrationDTO.class);
    }

    private void updateCustomerRegistration(CustomerRegistration existingCustomerRegistration, CustomerRegistrationDTO customerRegistrationDTO) {
        existingCustomerRegistration.setFirstName(customerRegistrationDTO.getFirstName());
        existingCustomerRegistration.setLastName(customerRegistrationDTO.getLastName());
        existingCustomerRegistration.setEmail(customerRegistrationDTO.getEmail());
        existingCustomerRegistration.setAddress(customerRegistrationDTO.getAddress());
        existingCustomerRegistration.setPhoneNumber(customerRegistrationDTO.getPhoneNumber());
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Customer ID cannot be null.");
        }
        CustomerRegistration existingCustomerRegistration = customerRegistrationRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRegistrationRepository.delete(existingCustomerRegistration);
        log.info("Customer with id {} was deleted.", id);
    }
}