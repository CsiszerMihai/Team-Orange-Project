package com.orange.team.models.dtos;

import lombok.Data;

@Data
public class CustomerRegistrationDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
}