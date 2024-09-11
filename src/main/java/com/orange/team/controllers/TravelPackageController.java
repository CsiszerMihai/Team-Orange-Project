package com.orange.team.controllers;

import com.orange.team.models.dtos.TravelPackageDTO;
import com.orange.team.services.TravelPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @PostMapping ("api/travel_packages")
    public ResponseEntity<TravelPackageDTO> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        return ResponseEntity.ok(travelPackageService.createTravelPackage(travelPackageDTO));
    }
}