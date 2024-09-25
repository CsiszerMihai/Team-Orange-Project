package com.orange.team.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.team.exceptions.TravelPackageNotFoundException;
import com.orange.team.models.dtos.TravelPackageDTO;
import com.orange.team.models.entities.TravelPackage;
import com.orange.team.repositories.TravelPackageRepository;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Period;
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
    public TravelPackageDTO updateTravelPackageById(Long id, TravelPackageDTO travelPackageDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Travel Package ID cannot be null.");
        }
        TravelPackage existingPackage = travelPackageRepository.findById(id)
                .orElseThrow(() -> new TravelPackageNotFoundException(id));

        updateExistingPackage(existingPackage, travelPackageDTO);
        TravelPackage updatedPackage = travelPackageRepository.save(existingPackage);
        log.info("Travel Package with id {} was updated", updatedPackage.getId());

        return objectMapper.convertValue(updatedPackage, TravelPackageDTO.class);
    }

    private void updateExistingPackage(TravelPackage existingPackage, TravelPackageDTO travelPackageDTO) {
        existingPackage.setPackageName(travelPackageDTO.getPackageName());
        existingPackage.setDestination(travelPackageDTO.getDestination());
        existingPackage.setPackageDescription(travelPackageDTO.getPackageDescription());
        existingPackage.setPackageDuration(travelPackageDTO.getPackageDuration());
        existingPackage.setPricePerPerson(travelPackageDTO.getPricePerPerson());
        existingPackage.setAvailableDate(travelPackageDTO.getAvailableDate());
    }

    @Override
    public void deleteTravelPackageById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Travel Package ID cannot be null.");
        }
        TravelPackage existingPackage = travelPackageRepository.findById(id)
                .orElseThrow(() -> new TravelPackageNotFoundException(id));

        travelPackageRepository.delete(existingPackage);
        log.info("Travel Package with id {} was deleted", id);
    }
}