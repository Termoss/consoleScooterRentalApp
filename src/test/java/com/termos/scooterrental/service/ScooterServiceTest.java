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
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void test_addingScooter() throws ScooterRentException {
        // given
        String model = "123";
        Integer maxSpeed = 39;
        BigDecimal rentalPrice = new BigDecimal(123);
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        when(scooterRepository.save(any())).thenReturn(scooter);
        ScooterService scooterService = new ScooterService( null, null, null, null);

        // when then
        scooterService.addingScooter(model, maxSpeed, rentalPrice);
    }

    @Test(expected = ScooterRentException.class)
    public void test_addingScooter_max_speed_too_high() throws ScooterRentException {
        // given
        String model = "123";
        Integer maxSpeed = 123;
        BigDecimal rentalPrice = new BigDecimal(123);
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        when(scooterRepository.save(any())).thenReturn(scooter);
        ScooterService scooterService = new ScooterService( null, null, null, null);

        // when then
        scooterService.addingScooter(model, maxSpeed, rentalPrice);
    }

    @Test
    public void test_rentScooter() throws ScooterRentException {
        // given
        long scooterId = 123L;
        Long accountId = 123L;
        Optional<Scooter> scooterOpt = Optional.of(new Scooter());
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.of(new UserAccount());
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter(scooterId, accountId);
    }


    public void test_rentScooter_no_scooter_found() throws ScooterRentException {
        // given
        long scooterId = 123L;
        Long accountId = 123L;
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.of(new UserAccount());
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter(scooterId, accountId);
    }

    @Test
    public void test_rentScooter_no_user_found() throws ScooterRentException {
        // given
        long scooterId = 123L;
        Long accountId = 123L;
        Optional<Scooter> scooterOpt = Optional.of(new Scooter());
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter(scooterId, accountId);
    }

    @Test
    public void test_rentScooter_no_scooter_found_no_user_found() throws ScooterRentException {
        // given
        long scooterId = 123L;
        Long accountId = 123L;
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter(scooterId, accountId);
    }

    @Test
    public void test_rentScooter_ScooterTerminalParseException() throws ScooterTerminalParseException, ScooterRentException {
        // given
        long scooterId = 123L;
        Long accountId = 123L;
        doAnswer(p -> {
            throw new ScooterTerminalParseException("", new Exception());
        }).when(readInputComponent.readAnswer(any(), any(), any()));
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter(scooterId, accountId);
    }

    @Test
    public void test_rentScooter_nd() throws ScooterTerminalParseException, ScooterRentException {
        // given
        long scooterId = 123L;
        Long accountId = 123L;
        doAnswer(p -> {
            throw new RuntimeException();
        }).when(readInputComponent.readAnswer(any(), any(), any()));
        Optional<Scooter> scooterOpt = Optional.empty();
        when(scooterRepository.findById(any())).thenReturn(scooterOpt);
        Optional<UserAccount> userOpt = Optional.empty();
        when(userAccountService.findById(any())).thenReturn(userOpt);
        when(rentRepository.save(any())).thenReturn(new Rent());
        ScooterService scooterService = new ScooterService(null, scooterRepository, rentRepository, userAccountService);

        // when then
        scooterService.rentScooter(scooterId, accountId);
    }

}