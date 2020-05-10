package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RentRepository extends JpaRepository<Rent, Long> {
    void deleteRentById(Long rentId);
}
