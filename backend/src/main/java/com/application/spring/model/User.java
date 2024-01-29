package com.application.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents a user in the application.
 * Users can select up to 5 sectors.
 * 
 * @param id        Unique identifier for the user.
 * @param name      The name of the user.
 * @param terms     Indicates whether the user has accepted the terms.
 * @param sectors   The sectors the user has selected.
 * @param session   Session for the user. Used to update user data,
 *                  without having to create a new user.
 */
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean terms;

    @ManyToMany(cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "user_sector", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private List<Sector> sectors;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private UserSession session;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String name, Boolean terms, List<Sector> sectors, UserSession session) {
        this.name = name;
        this.terms = terms;
        this.sectors = sectors;
        this.session = session;
    }
}