package com.termos.scooterrental.model;


import javax.persistence.*;
import java.math.BigDecimal;

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

    public Scooter(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public ScooterDock getScooterDock() {
        return scooterDock;
    }

    public void setScooterDock(ScooterDock scooterDock) {
        this.scooterDock = scooterDock;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", rentalPrice=" + rentalPrice +
                '}';
    }
}

