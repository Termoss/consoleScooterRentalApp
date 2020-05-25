package com.termos.scooterrental.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    private ZonedDateTime createdDate;

    private ZonedDateTime scooterReturnedDate;

    private boolean rented;

}
