package com.termos.scooterrental;

import com.termos.scooterrental.console.ConsoleInteractionComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootConsoleApplication implements CommandLineRunner {
    private final ConsoleInteractionComponent consoleInteractionComponent;

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) {
        consoleInteractionComponent.run();
        exit(0);
    }

}