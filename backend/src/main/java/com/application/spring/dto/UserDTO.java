package com.application.spring.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

/**
 * Represents a user data transfer object.
 */
@Data
public class UserDTO {
    private String name;
    private Boolean terms;
    private List<SectorDTO> sectors;
    private UserSessionDTO session;

    // Constructors, getters, and setters
    public UserDTO() {
    }
    
    public UserDTO(String name) {
        this.name = name;
    }

    @JsonCreator
    public UserDTO(String name, Boolean terms, List<SectorDTO> sectors, UserSessionDTO session) {
        this.name = name;
        this.terms = terms;
        this.sectors = sectors;
        this.session = session;
    }
}