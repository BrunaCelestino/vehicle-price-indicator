package br.com.bk.vehicle.price.indicator.infrastructure.logger;

import org.junit.jupiter.api.Test;

class LOGTest {


    @Test
    void when_info_shouldLogAtInfoLevel() {
        String message = "Test message";
        Object[] args = {"arg1"};

        LOG.info(message, args);
    }

    @Test
    void when_warn_shouldLogAtWarnLevel() {
        String message = "Test warning";

        LOG.warn(message);
    }

    @Test
    void when_error_shouldLogAtErrorLevel() {
        String message = "Test error";

        LOG.error(message);
    }

    @Test
    void when_log_shouldLogAtDefinedLevel() {
        String message = "Test log";
        Object[] args = {"arg1"};

        LOG.log(org.slf4j.event.Level.DEBUG, message, args);
    }
}
