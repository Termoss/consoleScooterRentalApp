package com.termos.scooterrental.service;

import com.termos.scooterrental.component.ReadInputComponent;
import com.termos.scooterrental.exception.ScooterTerminalParseException;
import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.RentRepository;
import com.termos.scooterrental.repository.ScooterRepository;
import com.termos.scooterrental.repository.UserAccountRepository;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserAccountServiceTest {
@Mock
private  UserAccountRepository userAccountRepository;
@Mock
private UserAccountService userAccountService;
@Mock
private ReadInputComponent readInputComponent;

    void findAllUsers() {
        //given
        List<UserAccount> list = new ArrayList<>();
        Mockito.when(userAccountRepository.findAll()).thenReturn(list);
      //  UserAccountService userAccountService = new UserAccountService(null, userAccountRepository);


        //when
        List<UserAccount> result = userAccountService.findAllUsers();

        //then
        Assert.assertFalse("lista jest pusta", result.isEmpty());
    }

}