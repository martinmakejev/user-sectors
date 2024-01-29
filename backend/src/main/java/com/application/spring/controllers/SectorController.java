package com.application.spring.controllers;

import com.application.spring.dto.SectorDTO;
import com.application.spring.services.sector.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    /**
    * Retrieves all sectors from the sector repository and builds a sector tree.
    *
    * @return a list of sectors representing the sector tree
    */
    @GetMapping
    public List<SectorDTO> getAllSectors() {
        return sectorService.getAllSectors();
    }
}