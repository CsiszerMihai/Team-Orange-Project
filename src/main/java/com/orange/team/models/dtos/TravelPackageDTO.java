package com.orange.team.models.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class TravelPackageDTO {

    private long id;
    private String packageName;
    private String destination;
    private String packageDescription;
    private Period duration;
    private double pricePerPerson;
    private LocalDate availableDate;
}