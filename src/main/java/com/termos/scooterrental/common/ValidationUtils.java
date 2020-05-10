package com.termos.scooterrental.common;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Integer MIN_AGE = 1;
    private static final Integer MAX_AGE = 100;
    private static final Integer MIN_SPEED = 1;
    private static final Integer MAX_SPEED = 40;
    private static final String emailPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

        public static boolean isCorrectedEmail(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isUncorrectedAge(Integer age) {
        return age < MIN_AGE || age > MAX_AGE;
    }

    public static boolean isUncorrectedMaxSpeed(Integer maxSpeed) {
        return maxSpeed < MIN_SPEED || maxSpeed > MAX_SPEED;
    }
}
