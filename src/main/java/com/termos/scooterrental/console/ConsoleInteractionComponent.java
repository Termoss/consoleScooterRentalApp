package com.termos.scooterrental.console;

import com.termos.scooterrental.common.ValidationUtils;
import com.termos.scooterrental.component.ErrorLoggingComponent;
import com.termos.scooterrental.component.ReadInputComponent;
import com.termos.scooterrental.exception.ScooterRentException;
import com.termos.scooterrental.exception.ScooterTerminalParseException;
import com.termos.scooterrental.exception.UserAddException;
import com.termos.scooterrental.model.Rent;
import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.service.ScooterService;
import com.termos.scooterrental.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsoleInteractionComponent {
    private final ReadInputComponent readInputComponent;
    private final UserAccountService userAccountService;
    private final ScooterService scooterService;
    private final ErrorLoggingComponent errorLoggingComponent;

    public void run() {

        printMenu();
        boolean end = false;
        while (!end) {
            String inputStr = readInputComponent.getNext();
            ;
            int inputInt;
            try {
                inputInt = Integer.parseInt(inputStr);
            } catch (NumberFormatException ex) {
                log.error("Number format exception. Provide number 0-9 instead of: {}", inputStr);
                continue;
            }

            switch (inputInt) {
                case 0:
                    end = true;
                    break;
                case 1:
                    addingUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    findAllUsers();
                    break;
                case 4:
                    addingScooter();
                    break;
                case 5:
                    deleteScooter();
                    break;
                case 6:
                    findAllScooters();
                    break;
                case 7:
                    rentScooter();
                    break;
                case 8:
                    returnScooter();
                    break;
                case 9:
                    addingScooterDock();
                    break;
                case 10:
                    deleteScooterDock();
                    break;
                default:
                    log.error("Number format exception. Provide number 0-10 instead of: {}", inputStr);
            }
            printMenu();
        }
        System.out.println("End of my program");
    }

    private void deleteScooterDock() {
        try {
            Long scooterDockId = readInputComponent.readAnswer("Enter scooter dock ID", Long::valueOf, "scooter dock has to be number");
            scooterService.deleteScooterDock(scooterDockId);
        } catch (RuntimeException ex) {
            log.error("Cannot delete scooter dock", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        }
    }

    private void addingScooterDock() {
        try {
            String dockName = readInputComponent.readAnswerAsStr("Enter the name");
            scooterService.addingScooterDock(dockName);
        } catch (RuntimeException ex) {
            log.error("Cannot scooter dock", ex);
        }
    }

    private void returnScooter() {
        try {
            Long rentDockId = readInputComponent.readAnswer("Enter rent ID", Long::valueOf, "Rent ID has to be number");
            scooterService.returnScooter(rentDockId);
        } catch (RuntimeException ex) {
            log.error("Cannot delete rent", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        }
    }

    private void rentScooter() {
        try {
            Long scooterId = readInputComponent.readAnswer("Enter scooter ID", ValidationUtils::parseNaturalLong, "Scooter id has to be a natural number");
            Long accountId = readInputComponent.readAnswer("Enter account ID", ValidationUtils::parseNaturalLong, "Account id has to be a natural number");
            Rent rentSaved = scooterService.rentScooter(scooterId, accountId);
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

    private void findAllScooters() {
        try {
            List<Scooter> scooters = scooterService.findAllScooters();
            for (Scooter scooter : scooters) {
                System.out.println(scooter);
            }
        } catch (RuntimeException ex) {
            log.error("find all scooters", ex);
        }
    }

    private void deleteScooter() {
        try {
            Long scooterId = readInputComponent.readAnswer("Enter scooter ID", ValidationUtils::parseNaturalLong, "Scooter id has to be a natural number");
            scooterService.deleteScooter(scooterId);
        } catch (RuntimeException ex) {
            log.error("Delete scooter", ex);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        }
    }

    private void addingScooter() {
        try {
            String model = readInputComponent.readAnswerAsStr("Enter scooter Model");
            Integer maxSpeed = readInputComponent.readAnswer("Enter scooter max speed ", ValidationUtils::parseNaturalInt, "Scooter id has to be a natural number");
            BigDecimal rentalPrice = readInputComponent.readAnswer("Enter rental price", BigDecimal::new, "Scooter id has to be a number");
            scooterService.addingScooter(model, maxSpeed, rentalPrice);
        } catch (ScooterTerminalParseException ex) {
            errorLoggingComponent.logParseError(log, ex);
        } catch (ScooterRentException e) {
            log.error("Cannot add scooter", e);
        } catch (RuntimeException ex) {
            log.error("Cannot add scooter", ex);
        }
    }

    private void findAllUsers() {
        try {
            List<UserAccount> allUsers = userAccountService.findAllUsers();
            allUsers.forEach(System.out::println);
        } catch (RuntimeException ex) {
            log.error("Cannot add user", ex);
        }
    }

    private void addingUser() {
        try {
            String username = readInputComponent.readAnswerAsStr("\n" + "Enter the user's name");
            Integer ownerAge = readInputComponent.readAnswer("Enter the age of the user", ValidationUtils::parseNaturalInt, "Age has to be a natural number");
            String email = readInputComponent.readAnswerAsStr("Enter the user's email address");

            userAccountService.addingUser(username, ownerAge, email);
        } catch (UserAddException e) {
            log.error("", e);
        } catch (ScooterTerminalParseException e) {
            errorLoggingComponent.logParseError(log, e);
        } catch (RuntimeException ex) {
            log.error("Cannot add user", ex);
        }
    }

    public void deleteUser() {
        try {
            String userAccountToDelete = readInputComponent.readAnswerAsStr("Enter user email");
            userAccountService.deleteUser(userAccountToDelete);
            log.info("User account was deleted");
        } catch (RuntimeException ex) {
            log.error("Cannot delete user", ex);
        }
    }

    void printMenu() {

        System.out.println(" ");
        System.out.println("Scooter rental App \n" +
                "Enter menu id: \n" +
                "1. Create user:\n" +
                "2. Delete user \n" +
                "3. Find all user's\n" +
                "4. Add scooter\n" +
                "5. Delete scooter\n" +
                "6. Find all scooter's \n" +
                "7. Rent scooter\n" +
                "8. Return scooter\n" +
                "9. Add scooter dock\n" +
                "10. Delete scooter dock\n" +
                "0. Exit\n");
    }
}
