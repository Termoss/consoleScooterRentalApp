package com.termos.scooterrental;

import com.termos.scooterrental.component.ReadInputComponent;
import com.termos.scooterrental.service.ScooterService;
import com.termos.scooterrental.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpringBootConsoleApplication implements CommandLineRunner {
    private final ReadInputComponent readInputComponent;
    private final UserAccountService userAccountService;
    private final ScooterService scooterService;

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) {
        String inputStr;
        int inputInt;

        printMenu();
        outer:
        while (true) {
            inputStr = readInputComponent.getNext();
            try {
                inputInt = Integer.parseInt(inputStr);
            } catch (NumberFormatException ex) {
                log.error("Number format exception. Provide number 0-9 instead of: {}", inputStr);
                continue;
            }

            switch (inputInt) {
                case 0:
                    break outer;
                case 1:
                    userAccountService.addingUser();
                    break;
                case 2:
                    userAccountService.deleteUser();
                    break;
                case 3:
                    userAccountService.findAllUsers();
                    break;
                case 4:
                    scooterService.addingScooter();
                    break;
                case 5:
                    scooterService.deleteScooter();
                    break;
                case 6:
                    scooterService.findAllScooters();
                    break;
                case 7:
                    scooterService.rentScooter();
                    break;
                case 8:
                    scooterService.returnScooter();
                    break;
                case 9:
                    scooterService.addingScooterDock();
                    break;
                case 10:
                    scooterService.deleteScooterDock();
                    break;
                default:
                    log.error("Number format exception. Provide number 0-10 instead of: {}", inputStr);
                    continue outer;

            }
            printMenu();
        }
        System.out.println("End of my program");

        exit(0);
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