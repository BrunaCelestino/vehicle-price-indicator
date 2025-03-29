package br.com.bk.vehicle.price.indicator.infrastructure.logger;

import static org.slf4j.event.Level.ERROR;
import static org.slf4j.event.Level.INFO;
import static org.slf4j.event.Level.WARN;

import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class LOG {

    public static void info(String message, Object... args) {
        log(INFO, message, args);
    }

    public static void warn(String message) {
        log(WARN, message);
    }

    public static void error(String message) {
        log(ERROR, message);
    }

    public static void log(Level level, String message, Object... args) {
        var log = LoggerFactory.getLogger("root");
        log.atLevel(level).log(String.format(message, args));
    }
}
