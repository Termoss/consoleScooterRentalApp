package com.termos.scooterrental;

import com.termos.scooterrental.model.Rent;
import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.repository.RentRepository;
import com.termos.scooterrental.time.TimeUtils;
import com.termos.scooterrental.model.ScooterDock;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.ScooterDockRepository;
import com.termos.scooterrental.repository.ScooterRepository;
import com.termos.scooterrental.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import lombok.Lombok;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootConsoleApplication implements CommandLineRunner {
    ScooterDockRepository scooterDockRepository;
    ScooterRepository scooterRepository;
    UserAccountRepository userAccountRepository;
    RentRepository rentRepository;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        //SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner input = new Scanner(System.in);
        int inputInt;

        while (true) {
            System.out.println(" ");
            System.out.println("Scooter rental App");
            System.out.println("Enter menu id:");
            System.out.println("1. Create user:");
            System.out.println("2. Delete user");
            System.out.println("3. Find all user's");
            System.out.println("4. Add scooter");
            System.out.println("5. Delete scooter");
            System.out.println("6. Find all scooter's ");
            System.out.println("7. Rent scooter");
            System.out.println("8. Add scooter dock");
            System.out.println("9. Delete scooter dock");
            System.out.println("0. Exit");
            inputInt = input.nextInt();
            if (inputInt == 0) {
                break;
            }
            if (inputInt == 1) {
                addingUser();
            }
            if (inputInt == 2) {
                deleteUser();
            }
            if (inputInt == 3) {
                findAllUsers();
            }
            if (inputInt == 4) {
                addingScooter();
                            }
            if (inputInt == 5) {
                deleteScooter();
            }
            if (inputInt == 6) {
                findAllScooters();
            }
            if (inputInt == 7) {
                rentScooter();
            }
            if (inputInt == 8) {
                addingScooterDock();
            }
            if (inputInt == 8) {
                deleteScooterDock();
            }
        }
        System.out.println("End of my program");

        exit(0);
    }

    void deleteUser(){
        String userAccountToDelete = readAnswer("Enter user email");
        userAccountRepository.deleteByOwnerEmail(userAccountToDelete);
        }

    void findAllUsers(){
        List<UserAccount> users = userAccountRepository.findAll();
                for(UserAccount userAccount : users){
                    System.out.println(userAccount);
                }
    }
    void findAllScooters(){
        List<Scooter> scooters = scooterRepository.findAll();
        for (Scooter scooter : scooters) {
            System.out.println(scooter);
        }
    }
    void addingScooter(){
        Scooter scooter1 = new Scooter();

        String model = readAnswer("Enter scooter Model");
        scooter1.setModelName(model);

        String maxSpeed = readAnswer("Enter scooter max speed ");
        scooter1.setMaxSpeed(Integer.valueOf(maxSpeed));

        String rentalPrice = readAnswer("Enter rental price");
        scooter1.setRentalPrice(BigDecimal.valueOf(Long.parseLong(rentalPrice)));
        scooterRepository.save(scooter1);
            }
    void rentScooter(){
        Rent rent1 = new Rent();
        String scooterId = readAnswer("Enter scooter ID");
        rent1.setScooterId(Long.valueOf(scooterId));

        String accountId = readAnswer("Enter account ID");
        rent1.setUserAccountId(Long.valueOf(accountId));
        rent1.setCreatedDate(TimeUtils.NowTimeStamp());
        rentRepository.save(rent1);
    }
    void deleteScooter(){
        String scooterId = readAnswer("Enter scooter ID");
        scooterRepository.deleteScooterById(Long.valueOf(scooterId));
    }
    void addingUser() {
        UserAccount user1 = new UserAccount();

        String username = readAnswer("\n" + "Enter the user's name");
        user1.setOwnerUsername(username);

        user1.setCreatedDate(TimeUtils.NowTimeStamp());

        String age = readAnswer("Enter the age of the user");
        user1.setOwnerAge(Integer.valueOf(age));

        String email = readAnswer("Enter the user's email address");
        user1.setOwnerEmail(email);

        userAccountRepository.save(user1);
    }
    void addingScooterDock(){
        ScooterDock scooterDock = new ScooterDock();

        String dockName = readAnswer("Enter the name");
        scooterDock.setDockName(dockName);
        scooterDockRepository.save(scooterDock);

    }
    void deleteScooterDock(){
        String scooterDockId = readAnswer("Enter scooter dock ID");
        scooterDockRepository.deleteScooterDockById(Long.valueOf(scooterDockId));


    }


    String readAnswer(String question) {
        String answer;
        while (true) {
            System.out.println(question);
            answer = scan.nextLine();
            if (answer == null || answer.isEmpty()) {
                System.out.println("The value cannot be empty");
            } else {
                break;
            }
        }
        return answer;

    }
}