package com.termos.scooterrental.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @JoinColumn(name = "scooter_id", referencedColumnName = "id")
    private Long scooterId;


    @JoinColumn (name = "user_account_id", referencedColumnName = "id")
    private Long userAccountId;

    private Timestamp createdDate;

}
