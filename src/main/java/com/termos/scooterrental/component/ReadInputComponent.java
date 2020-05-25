package com.termos.scooterrental.component;

import com.termos.scooterrental.exception.ScooterTerminalParseException;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.function.Function;

@Component
public class ReadInputComponent {
    private final Scanner scan = new Scanner(System.in);


    public <T> T readAnswer(String question, Function<String, T> parseFunction, String errorMsg) throws ScooterTerminalParseException {
        String answer = readAnswerAsStr(question);
        try {
            return parseFunction.apply(answer);
        } catch (IllegalArgumentException ex) {
            throw new ScooterTerminalParseException(errorMsg, ex);
        }
    }

    public String readAnswerAsStr(String question) {
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

    public String getNext() {
        return scan.next();
    }
}
