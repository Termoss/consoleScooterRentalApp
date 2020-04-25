package com.termos.scooterrental.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ownerEmail;
    private String ownerUsername;
    private Integer ownerAge;
    private Timestamp createdDate;
     @OneToOne(mappedBy = "userAccount")
     private Scooter scooter;

    public UserAccount() {
    }

    public UserAccount(Long id, String ownerEmail, String ownerUsername, Integer ownerAge, Timestamp createdDate) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.ownerUsername = ownerUsername;
        this.ownerAge = ownerAge;
        this.createdDate = createdDate;
    }

}
