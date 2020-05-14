package com.termos.scooterrental.component;

import com.termos.scooterrental.exception.ScooterTerminalParseException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ErrorLoggingComponent {
    public void logParseError(Logger log, ScooterTerminalParseException ex) {
        log.error(ex.getMessage());
        log.debug("PARSE ERROR ", ex);
    }
}
