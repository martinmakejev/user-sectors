package com.application.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
/**
 * Represents a sector in the application.
 * 
 * @param id         Unique identifier for the sector.
 * @param name       The name of the sector.
 * @param parent     The parent sector to which this sector belongs.
 * @param subSectors The list of sub-sectors that belong to this sector.
 * @param users      The users that have selected this sector.
 */
@Entity
@Getter
@Setter
public class Sector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

   /**
   * The parent sector to which this sector belongs.
   */
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "parent_id")
  private Sector parent;

  /**
   * The list of sub-sectors that belong to this sector.
   */
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Sector> subSectors;

  @ManyToMany(mappedBy = "sectors")
  private List<User> users;

  // Constructors and other methods...

  public Sector() {
  }

  public Sector(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getParentId() {
    return (parent != null) ? parent.getId() : null;
  }
}