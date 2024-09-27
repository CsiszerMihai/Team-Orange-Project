package com.orange.team.repositories;

import com.orange.team.models.entities.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, Long> {
}
