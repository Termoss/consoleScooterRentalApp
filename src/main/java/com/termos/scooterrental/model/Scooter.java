package com.termos.scooterrental.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
@Table
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    private  String modelName;
    private  Integer maxSpeed;
    private BigDecimal rentalPrice;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scooter_dock_id")
    private  ScooterDock scooterDock;


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;
    public Scooter() {
    }

}

