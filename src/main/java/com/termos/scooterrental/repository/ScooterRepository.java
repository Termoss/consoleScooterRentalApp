package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.model.ScooterDock;
import com.termos.scooterrental.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
public interface ScooterRepository  extends JpaRepository<Scooter, Long> {
    @Transactional
    void deleteScooterById(Long scooterId);
}
