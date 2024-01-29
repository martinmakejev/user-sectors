package com.application.spring.dao.sector;

import java.util.List;
import com.application.spring.model.Sector;

public interface SectorDAO {

    List<Sector> getAllSectors();

    Sector getSector(Long id);
} 
