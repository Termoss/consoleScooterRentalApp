package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.ScooterDock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterDockRepository extends JpaRepository<ScooterDock, Long> {
    void deleteScooterDockById(Long dockId);

}
