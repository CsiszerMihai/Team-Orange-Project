package com.orange.team.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "travel_packages")

public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "package_name")
    private String name;
    @Column(name = "destination")
    private String destination;
    @Column(name = "package_description")
    private String description;
    @Column(name = "duration")
    private Period duration;
    @Column(name = "price_per_person")
    private double price;
    @Column(name = "available_dates")
    private LocalDate availableDates;
}