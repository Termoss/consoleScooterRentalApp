package com.termos.scooterrental.service;

import com.termos.scooterrental.common.ValidationUtils;
import com.termos.scooterrental.exception.ScooterRentException;
import com.termos.scooterrental.model.Rent;
import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.model.ScooterDock;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.RentRepository;
import com.termos.scooterrental.repository.ScooterDockRepository;
import com.termos.scooterrental.repository.ScooterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterDockRepository scooterDockRepository;
    private final ScooterRepository scooterRepository;
    private final RentRepository rentRepository;
    private final UserAccountService userAccountService;

    public List<Scooter> findAllScooters() {
        return scooterRepository.findAll();
    }

    public Optional<Scooter> findById(Long id) {
        return scooterRepository.findById(id);
    }

    public void addingScooter(String model, Integer maxSpeed, BigDecimal rentalPrice) throws ScooterRentException {
        if (ValidationUtils.isUncorrectedMaxSpeed(maxSpeed)) {
            throw ScooterRentException.createScooterWithNotValidSpeed(maxSpeed);
        }
        Scooter scooter1 = new Scooter();
        scooter1.setModelName(model);
        scooter1.setMaxSpeed(maxSpeed);
        scooter1.setRentalPrice(rentalPrice);
        Scooter scooterSaved = scooterRepository.save(scooter1);
        log.info("Scooter saved with id: {}", scooterSaved.getId());
    }

    public Rent rentScooter(Long scooterId, Long accountId) throws ScooterRentException {
        Rent rent1 = new Rent();
        Optional<Scooter> scooter = findById(scooterId);
        if (scooter.isEmpty()) {
            throw ScooterRentException.createScooterDoesntExists(scooterId);
        } else {
            rent1.setScooter(scooter.get());
        }
        Optional<UserAccount> userAccount = userAccountService.findById(accountId);
        if (userAccount.isEmpty()) {
            throw ScooterRentException.createUserDoesntExists(accountId);
        } else {
            rent1.setScooter(scooter.get());
        }
        rent1.setCreatedDate(ZonedDateTime.now());
        Rent rentSaved = rentRepository.save(rent1);
        log.info("Rent finished successful with id: {}", rentSaved.getId());
        return rentSaved;
    }

    @Transactional
    public void deleteScooter(Long scooterId) {
        scooterRepository.deleteScooterById(scooterId);
    }

    public void addingScooterDock(String dockName) {
        ScooterDock scooterDock = new ScooterDock();
        scooterDock.setDockName(dockName);
        scooterDockRepository.save(scooterDock);
    }

    public void deleteScooterDock(Long scooterDockId) {
        scooterDockRepository.deleteScooterDockById(scooterDockId);
    }

    @Transactional
    public void returnScooter(Long rentDockId) {
        Rent scooter = rentRepository.getOne(rentDockId);
        scooter.setScooterReturnedDate(ZonedDateTime.now());
        scooter.setRented(false);
        rentRepository.save(scooter);
    }
}
