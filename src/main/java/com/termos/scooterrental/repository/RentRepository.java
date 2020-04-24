package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.Rent;
import com.termos.scooterrental.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository

public interface RentRepository extends JpaRepository<Rent, Long>  {
    @Transactional
    void deleteRentById(Long scooterId);

}
