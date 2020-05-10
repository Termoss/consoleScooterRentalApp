package com.termos.scooterrental.service;

import com.termos.scooterrental.common.ValidationUtils;
import com.termos.scooterrental.component.ErrorLoggingComponent;
import com.termos.scooterrental.component.ReadInputComponent;
import com.termos.scooterrental.exception.ScooterRentException;
import com.termos.scooterrental.exception.ScooterTerminalParseException;
import com.termos.scooterrental.model.Rent;
import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.model.ScooterDock;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.RentRepository;
import com.termos.scooterrental.repository.ScooterDockRepository;
import com.termos.scooterrental.repository.ScooterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScooterService {
    private final ReadInputComponent readInputComponent;
    private final ErrorLoggingComponent errorLoggingComponent;
    private final ScooterDockRepository scooterDockRepository;
    private final ScooterRepository scooterRepository;
    private final RentRepository rentRepository;
    private final UserAccountService userAccountService;

    public void findAllScooters() {
        try {
            List<Scooter> scooters = scooterRepository.findAll();
            for (Scooter scooter : scooters) {
                System.out.println(scooter);
            }
        } catch (RuntimeException ex) {
            log.error("find all scooters", ex);
        }
    }

    public Optional<Scooter> findById(Long id) {
        return scooterRepository.findById(id);
    }

    public void addingScooter() {
        try {
            Scooter scooter1 = new Scooter();

            String model = readInputComponent.readAnswerAsStr("Enter scooter Model");
            scooter1.setModelName(model);

            Integer maxSpeed = readInputComponent.readAnswer("Enter scooter max speed ", Integer::valueOf, "Scooter id has to be a natural number");
            if (ValidationUtils.isUncorrectedMaxSpeed(maxSpeed)) {
                throw ScooterRentException.createScooterWithNotValidSpeed(Integer.valueOf(maxSpeed));
            } else {
                scooter1.setMaxSpeed(maxSpeed);
            }
            BigDecimal rentalPrice = readInputComponent.readAnswer("Enter rental price", BigDecimal::new, "Scooter id has to be a number");
            scooter1.setRentalPrice(rentalPrice);
            Scooter scooterSaved = scooterRepository.save(scooter1);
            log.info("Scooter saved with id: {}", scooterSaved.getId());
        } catch (RuntimeException ex) {
            log.error("Cannot add scooter", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        } catch (ScooterRentException e) {
            e.printStackTrace();
        }
    }

    public void rentScooter() {
        try {
            Rent rent1 = new Rent();
            Long scooterId = readInputComponent.readAnswer("Enter scooter ID", Long::valueOf, "Scooter id has to be a natural number");
            Optional<Scooter> scooter = findById(scooterId);
            if (scooter.isEmpty()) {
                throw ScooterRentException.createScooterDoesntExists(scooterId);
            } else {
                rent1.setScooter(scooter.get());
            }

            Long accountId = readInputComponent.readAnswer("Enter account ID", Long::valueOf, "Account id has to be a natural number");
            Optional<UserAccount> userAccount = userAccountService.findById(accountId);
            if (userAccount.isEmpty()) {
                throw ScooterRentException.createUserDoesntExists(accountId);
            } else {
                rent1.setScooter(scooter.get());
            }
            rent1.setCreatedDate(ZonedDateTime.now());
            Rent rentSaved = rentRepository.save(rent1);
            log.info("Rent finished successful with id: {}", rentSaved.getId());
        } catch (RuntimeException ex) {
            log.error("Rent scooter exception", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        } catch (ScooterRentException e) {
            if (log.isDebugEnabled()) {
                log.error("Cannot rent the scooter because: {}", e.getMessage(), e);
            } else {
                log.error("Cannot rent the scooter because: {}", e.getMessage());
            }
        }
    }

    public void deleteScooter() {
        try {
            Long scooterId = readInputComponent.readAnswer("Enter scooter ID", Long::valueOf, "Scooter id has to be a natural number");
            scooterRepository.deleteScooterById(scooterId);
        } catch (RuntimeException ex) {
            log.error("Delete scooter", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        }
    }


    public void addingScooterDock() {
        try {
            ScooterDock scooterDock = new ScooterDock();

            String dockName = readInputComponent.readAnswerAsStr("Enter the name");
            scooterDock.setDockName(dockName);
            scooterDockRepository.save(scooterDock);

        } catch (RuntimeException ex) {
            log.error("Cannot scooter dock", ex);
        }
    }

    public void deleteScooterDock() {
        try {
            Long scooterDockId = readInputComponent.readAnswer("Enter scooter dock ID", Long::valueOf, "scooter dock has to be number");
            scooterDockRepository.deleteScooterDockById(scooterDockId);

        } catch (RuntimeException ex) {
            log.error("Cannot delete scooter dock", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        }

    }

    public void returnScooter() {
        try {
            Long rentDockId = readInputComponent.readAnswer("Enter rent ID", Long::valueOf, "Rent ID has to be number");
            rentRepository.deleteRentById(rentDockId);

        } catch (RuntimeException ex) {
            log.error("Cannot delete rent", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        }
    }
}
