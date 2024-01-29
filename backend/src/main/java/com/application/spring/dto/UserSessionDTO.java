package com.application.spring.dto;

import lombok.Data;

@Data
public class UserSessionDTO {

    private String sessionToken;

    public UserSessionDTO() {
    }

    public UserSessionDTO(String sessionToken) {
        this.sessionToken = sessionToken;
    }

}
