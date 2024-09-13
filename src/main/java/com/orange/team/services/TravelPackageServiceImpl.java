package com.orange.team.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.team.models.dtos.TravelPackageDTO;
import com.orange.team.models.entities.TravelPackage;
import com.orange.team.repositories.TravelPackageRepository;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    private static final Logger log = LoggerFactory.getLogger(TravelPackageServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final TravelPackageRepository travelPackageRepository;

    public TravelPackageServiceImpl(ObjectMapper objectMapper, TravelPackageRepository travelPackageRepository) {
        this.objectMapper = objectMapper;
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackageEntity = objectMapper.convertValue(travelPackageDTO, TravelPackage.class);
        TravelPackage travelPackageEntityResponse = travelPackageRepository.save(travelPackageEntity);
        log.info("Travel Package with id {} was saved", travelPackageEntityResponse.getId());

        return objectMapper.convertValue(travelPackageEntityResponse, TravelPackageDTO.class);
    }

    @Override
    public List<TravelPackageDTO> getTravelPackages() {
        List<TravelPackage> travelPackages = travelPackageRepository.findAll();

        return travelPackages.stream()
                .map(travelPackage -> objectMapper.convertValue(travelPackage, TravelPackageDTO.class))
                .toList();
    }

    @Override
    public TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO) {
        TravelPackage existingPackage = travelPackageRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Travel Package not found with id: " + id));

        existingPackage.setName(travelPackageDTO.getName());
        existingPackage.setDestination(travelPackageDTO.getDestination());
        existingPackage.setDescription(travelPackageDTO.getDescription());
        existingPackage.setDuration(travelPackageDTO.getDuration());
        existingPackage.setPrice(travelPackageDTO.getPrice());
        existingPackage.setAvailableDates(travelPackageDTO.getAvailableDates());

        TravelPackage updatedPackage = travelPackageRepository.save(existingPackage);
        log.info("Travel Package with id {} was updated", updatedPackage.getId());

        return objectMapper.convertValue(updatedPackage,TravelPackageDTO.class);
    }

    @Override
    public void deleteTravelPackage(Long id) {
        TravelPackage existingPackage = travelPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel package not found with id: " + id));

        travelPackageRepository.delete(existingPackage);
        log.info("Travel Package with id {} was deleted", id);
    }
}