package com.application.spring.services.sector;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.spring.dao.sector.SectorDAO;
import com.application.spring.dto.SectorDTO;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorDAO sectorDAO;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all sectors.
     *
     * @return a list of SectorDTO objects representing all sectors
     */
    public List<SectorDTO> getAllSectors() {
        return sectorDAO.getAllSectors().stream()
                .filter(sector -> sector.getParentId() == null)
                .map(sector -> modelMapper.map(sector, SectorDTO.class))
                .collect(Collectors.toList());
    }
}