package com.application.spring.dto;

import java.util.List;
import lombok.Data;

@Data
public class SectorDTO {
    private Long id;
    private String name;
    private List<SectorDTO> subSectors;

    public SectorDTO() {
    }

    public SectorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SectorDTO(String name) {
        this.name = name;
    }

    public SectorDTO(Long id) {
        this.id = id;
    }
}