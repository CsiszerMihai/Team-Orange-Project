package com.orange.team.integration_tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.team.models.dtos.TravelPackageDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Period;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@AutoConfigureTestDatabase
class TravelPackageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTravelPackageShouldPass() throws Exception {
        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.setPackageName("Test Name");
        travelPackageDTO.setDestination("Test Destination");
        travelPackageDTO.setPackageDescription("Test Description");
        travelPackageDTO.setPackageDuration(Period.of(0, 1, 0));
        travelPackageDTO.setPricePerPerson(500);
        travelPackageDTO.setAvailableDate(LocalDate.now().plusDays(7));

        mockMvc.perform(post("/api/travel_packages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelPackageDTO)))
                .andExpect(status().isOk());
    }
}