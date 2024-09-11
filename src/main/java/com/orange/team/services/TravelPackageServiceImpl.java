package com.orange.team.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.team.models.dtos.TravelPackageDTO;
import com.orange.team.models.entities.TravelPackage;
import com.orange.team.repositories.TravelPackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}