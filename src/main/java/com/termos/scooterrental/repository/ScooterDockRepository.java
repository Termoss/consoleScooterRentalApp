package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.ScooterDock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@Repository
public interface ScooterDockRepository extends JpaRepository<ScooterDock, Long> {
    @Transactional
    void deleteScooterDockById(Long dockId);

}
