package com.termos.scooterrental.common;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Integer MIN_AGE = 1;
    private static final Integer MAX_AGE = 100;
    private static final Integer MIN_SPEED = 1;
    private static final Integer MAX_SPEED = 50;
//    private static final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final String EMAIL_PATTERN2 = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";

    public static boolean isCorrectedEmail(String email) {
        final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN2, Pattern.CASE_INSENSITIVE);
        return !emailPattern.matcher(email).matches();
    }

    public static boolean isUncorrectedAge(Integer age) {
        return age < MIN_AGE || age > MAX_AGE;
    }

    public static boolean isUncorrectedMaxSpeed(Integer maxSpeed) {
        return maxSpeed < MIN_SPEED || maxSpeed > MAX_SPEED;
    }

    public static Integer parseNaturalInt(String incoming) {
        Integer outgoing = Integer.valueOf(incoming);
        if (outgoing <= 0) {
            throw new IllegalArgumentException("Expected number greater than 0");
        }
        return outgoing;
    }

    public static Long parseNaturalLong(String incoming) {
        Long outgoing = Long.valueOf(incoming);
        if (outgoing <= 0) {
            throw new IllegalArgumentException("Expected number greater than 0");
        }
        return outgoing;
    }
}
