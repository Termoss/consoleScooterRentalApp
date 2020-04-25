package com.termos.scooterrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity
public class ScooterDock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String dockName;
    private Integer availablePlace;

    @OneToMany(mappedBy = "scooterDock", cascade = CascadeType.ALL)
    private Set<Scooter> scooters;


}
