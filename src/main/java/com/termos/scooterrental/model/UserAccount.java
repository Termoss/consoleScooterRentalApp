package com.termos.scooterrental.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ownerEmail;
    private String ownerUsername;
    private Integer ownerAge;
    private ZonedDateTime createdDate;
    @OneToOne(mappedBy = "userAccount")
    private Scooter scooter;

    public UserAccount() {
    }

}
