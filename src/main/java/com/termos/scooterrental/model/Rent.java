package com.termos.scooterrental.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScooterId() {
        return scooterId;
    }

    public void setScooterId(Long scooterId) {
        this.scooterId = scooterId;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", scooterId=" + scooterId +
                ", userAccountId=" + userAccountId +
                ", createdDate=" + createdDate +
                '}';
    }
}
