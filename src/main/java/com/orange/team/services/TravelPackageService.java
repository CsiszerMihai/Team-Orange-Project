package com.orange.team.services;

import com.orange.team.models.dtos.TravelPackageDTO;

import java.util.List;

public interface TravelPackageService {

    TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO);

    List<TravelPackageDTO> getTravelPackages();

    TravelPackageDTO updateTravelPackageById(Long id, TravelPackageDTO travelPackageDTO);

    void deleteTravelPackageById(Long id);

    List<TravelPackageDTO> searchTravelPackages(String destination);
}