package com.application.spring.repositories;

import com.application.spring.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called sectorRepository
// CRUD refers Create, Read, Update, Delete

public interface SectorRepository extends JpaRepository<Sector, Long> {
    // You can add custom query methods here if needed
}