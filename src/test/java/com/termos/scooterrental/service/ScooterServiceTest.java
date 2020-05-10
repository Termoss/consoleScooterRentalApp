package com.termos.scooterrental.service;

import com.termos.scooterrental.component.ReadInputComponent;
import com.termos.scooterrental.exception.ScooterRentException;
import com.termos.scooterrental.exception.ScooterTerminalParseException;
import com.termos.scooterrental.model.Rent;
import com.termos.scooterrental.model.Scooter;
import com.termos.scooterrental.model.UserAccount;
import com.termos.scooterrental.repository.RentRepository;
import com.termos.scooterrental.repository.ScooterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScooterServiceTest {
    @Mock
    private UserAccountService userAccountService;
    @Mock
    private ReadInputComponent readInputComponent;
    @Mock
    private ScooterRepository scooterRepository;
    @Mock
    private RentRepository rentRepository;
    @Test
    public void test_ScooterServiceTest() {
        //Given

        //When

        //Then
    }

    public void test_addingScooter() throws ScooterTerminalParseException {
        // given
        when(readInputComponent.readAnswerAsStr(any())).thenReturn("123");
        when(readInputComponent.readAnswer("Enter scooter max speed ", any(), any())).thenReturn(123);
        when(readInputComponent.readAnswer("Enter rental price", any(), any())).thenReturn(new BigDecimal(123));
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        when(scooterRepository.save(any())).thenReturn(scooter);
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, null, null, null);

        // when then
        scooterService.addingScooter();
    }

    public void test_rentScooter() throws ScooterTerminalParseException {
        // given
        when(readInputComponent.readAnswer(any(), any(), any())).thenReturn(123L);
        Optional<Scooter> scooterOpt = Optional.of(new Scooter());
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.of(new UserAccount());
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter();
    }


    public void test_rentScooter_no_scooter_found() throws ScooterTerminalParseException {
        // given
        when(readInputComponent.readAnswer(any(), any(), any())).thenReturn(123L);
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.of(new UserAccount());
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter();
    }

    @Test
    public void test_rentScooter_no_user_found() throws ScooterTerminalParseException, ScooterRentException {
        // given
        when(readInputComponent.readAnswer(any(), any(), any())).thenReturn(123L);
        Optional<Scooter> scooterOpt = Optional.of(new Scooter());
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter();
    }

    @Test
    public void test_rentScooter_no_scooter_found_no_user_found() throws ScooterTerminalParseException {
        // given
        when(readInputComponent.readAnswer(any(), any(), any())).thenReturn(123L);
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter();
    }

    @Test
    public void test_rentScooter_ScooterTerminalParseException() throws ScooterTerminalParseException {
        // given
        doAnswer(p -> {
            throw new ScooterTerminalParseException("", new Exception());
        }).when(readInputComponent.readAnswer(any(), any(), any()));
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter();
    }

    @Test
    public void test_rentScooter_nd() throws ScooterTerminalParseException {
        // given
        doAnswer(p -> {
            throw new RuntimeException();
        }).when(readInputComponent.readAnswer(any(), any(), any()));
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(readInputComponent, null, null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter();
    }

}