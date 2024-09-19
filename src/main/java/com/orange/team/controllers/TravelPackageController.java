package com.orange.team.controllers;

import com.orange.team.models.dtos.TravelPackageDTO;
import com.orange.team.services.TravelPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @PostMapping("api/travel_packages")
    public ResponseEntity<TravelPackageDTO> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        return ResponseEntity.ok(travelPackageService.createTravelPackage(travelPackageDTO));
    }

    @GetMapping("api/travel_packages")
    public ResponseEntity<List<TravelPackageDTO>> getTravelPackages() {
        return ResponseEntity.ok(travelPackageService.getTravelPackages());
    }

    @PutMapping("api/travel_packages")
    public ResponseEntity<TravelPackageDTO> updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO) {
        return ResponseEntity.ok(travelPackageService.updateTravelPackageById(id, travelPackageDTO));
    }

    @DeleteMapping("api/travel_packages")
    public ResponseEntity<Void> deleteTravelPackage(Long id) {
        travelPackageService.deleteTravelPackageById(id);
        return ResponseEntity.noContent().build();
    }
}