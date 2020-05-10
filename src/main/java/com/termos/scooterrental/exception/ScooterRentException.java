package com.termos.scooterrental.exception;

public class ScooterRentException extends Exception {
    public ScooterRentException(String msg) {
        super(msg);
    }

    public static ScooterRentException createUserDoesntExists(Long accountId) {
        return new ScooterRentException(String.format("Cannot find user by id: %s", accountId));
    }

    public static ScooterRentException createScooterDoesntExists(Long scooterId) {
        return new ScooterRentException(String.format("Cannot find scooter by id: %s", scooterId));
    }

    public static ScooterRentException createScooterWithNotValidSpeed(Integer scooterId) {
        return new ScooterRentException(String.format("Incorrect speed, try 1-50 %s", scooterId));
    }
}
