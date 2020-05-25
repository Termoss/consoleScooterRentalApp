package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
    void deleteScooterById(Long scooterId);
}
