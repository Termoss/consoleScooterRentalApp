package com.termos.scooterrental.service;

import com.termos.scooterrental.common.ValidationUtils;
import com.termos.scooterrental.exception.UserAddException;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public void addingUser(String username, Integer age, String email) throws UserAddException {
        if (ValidationUtils.isUncorrectedAge(age)) {
            throw UserAddException.incorrectAge(age);
        }
        if (ValidationUtils.isCorrectedEmail(email)) {
            throw UserAddException.incorrectEmailAdress(email);
        }
        UserAccount user1 = new UserAccount();
        user1.setOwnerUsername(username);
        user1.setOwnerAge(age);
        user1.setOwnerEmail(email);

        user1.setCreatedDate(ZonedDateTime.now());
        UserAccount userSaved = userAccountRepository.save(user1);
        log.info("Scooter saved with id: {}", userSaved.getId());
    }

    @Transactional
    public void deleteUser(String userAccountToDelete) {
        userAccountRepository.deleteByOwnerEmail(userAccountToDelete);
     }

    public List<UserAccount> findAllUsers() {
        return userAccountRepository.findAll();
    }

    public Optional<UserAccount> findById(Long id) {
        return userAccountRepository.findById(id);
    }

}
