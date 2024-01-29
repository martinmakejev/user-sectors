package com.application.spring.dao.sector;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.application.spring.model.Sector;
import com.application.spring.repositories.SectorRepository;

@Repository
public class SectorDAOImpl implements SectorDAO {
    
    @Autowired
    private SectorRepository sectorRepository;

    /**
     * Retrieves all sectors from the database.
     *
     * @return a list of Sector objects representing all sectors in the database.
     */
    @Override
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    /**
     * Retrieves a sector by its ID.
     *
     * @param id the ID of the sector to retrieve
     * @return the sector with the specified ID, or null if not found
     */
    @Override
    public Sector getSector(Long id) {
        return sectorRepository.findById(id).orElse(null);
    }
}
