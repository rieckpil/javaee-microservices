package de.rieckpil.messages.control;

import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;

/**
 *
 * @author rieckpil
 */
public class LoggerObserver {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    public void onImportantEventInvoked(@Observes @Priority(1) @Important String event) {
        log.info(String.format("Logging sync: %s", event));
    }

    public void onCriticalEventInvoked(@Observes @Priority(1) @Critical String event) {
        log.info(String.format("Logging sync: %s", event));
    }
}
