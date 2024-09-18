package com.orange.team.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "travel_packages")
@Getter
@Setter
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "destination")
    private String destination;
    @Column(name = "package_description")
    private String packageDescription;
    @Column(name = "duration")
    private Period duration;
    @Column(name = "price_per_person")
    private double pricePerPerson;
    @Column(name = "available_date")
    private LocalDate availableDate;
}