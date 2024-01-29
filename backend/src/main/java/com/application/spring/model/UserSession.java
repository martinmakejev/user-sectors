package com.application.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
* Represents a user session in the application.
* A user session is associated with a specific user and contains information such as the session token and creation timestamp.
*
* @param id           Unique identifier for the user session.
* @param createdAt    The timestamp at which the user session was created.
* @param sessionToken The session token associated with the user session.
* @param user         The user associated with the user session.
*/
@Entity
@Getter
@Setter
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Added created at so in future there could be possibility to handle session validation.
    private Timestamp createdAt;

    private String sessionToken;

    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    public UserSession() {
        this.createdAt = new Timestamp(new Date().getTime());
        this.sessionToken = UUID.randomUUID().toString();
    }

    public UserSession(User user) {
        this.user = user;
        this.createdAt = new Timestamp(new Date().getTime());
        this.sessionToken = UUID.randomUUID().toString();
    }

    public UserSession(String string) {
        this.sessionToken = string;
    }
}
