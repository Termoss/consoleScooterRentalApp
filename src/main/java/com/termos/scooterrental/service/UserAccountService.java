package com.termos.scooterrental.service;

import com.termos.scooterrental.common.ValidationUtils;
import com.termos.scooterrental.component.ReadInputComponent;
import com.termos.scooterrental.exception.UserAddException;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAccountService {
    private final ReadInputComponent readInputComponent;
    private final UserAccountRepository userAccountRepository;

    public void addingUser() {
        try {
            UserAccount user1 = new UserAccount();

            String username = readInputComponent.readAnswerAsStr("\n" + "Enter the user's name");
            user1.setOwnerUsername(username);

            user1.setCreatedDate(ZonedDateTime.now());

            String age = readInputComponent.readAnswerAsStr("Enter the age of the user");
            if (ValidationUtils.isUncorrectedAge(Integer.valueOf(age))) {
                throw UserAddException.incorrectAge(Integer.valueOf(age));
            } else {
                user1.setOwnerAge(Integer.valueOf(age));
            }
            String email = readInputComponent.readAnswerAsStr("Enter the user's email address");

            if (ValidationUtils.isCorrectedEmail(email)) {
                user1.setOwnerEmail(email);
            } else {
                throw UserAddException.incorrectEmailAdress(email);
            }
            UserAccount userSaved = userAccountRepository.save(user1);
            log.info("Scooter saved with id: {}", userSaved.getId());
        } catch (RuntimeException ex) {
            log.error("Cannot add user", ex);
        } catch (UserAddException e) {
            log.error("Cannot add user", e);
        }
    }

    public void deleteUser() {

        try {
            String userAccountToDelete = readInputComponent.readAnswerAsStr("Enter user email");
            userAccountRepository.deleteByOwnerEmail(userAccountToDelete);
        } catch (RuntimeException ex) {
            log.error("Cannot delete user", ex);
        }
    }

    public List<UserAccount> findAllUsers() {

//        List<UserAccount> list = new ArrayList<>();
//        userAccountRepository.findAll().iterator().forEachRemaining(list::add);
//        return list;
        List<UserAccount> allUsers = userAccountRepository.findAll();
        allUsers.forEach(System.out::println);

        return allUsers;
    }

    public Optional<UserAccount> findById(Long id) {
        return userAccountRepository.findById(id);
    }


}
