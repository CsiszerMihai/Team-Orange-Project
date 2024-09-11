package com.orange.team.models.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.Period;

@Data

public class TravelPackageDTO {

    private long id;
    private String name;
    private String destination;
    private String description;
    private Period duration;
    private double price;
    private LocalDate availableDates;
}