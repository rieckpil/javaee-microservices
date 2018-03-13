/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.messages.control;

import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.event.ObservesAsync;

/**
 *
 * @author rieckpil
*/
public class AsyncLoggerObserver {
    
    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    public void onImportantEventInvoked(@ObservesAsync @Priority(5) @Important String event) {
        log.info(String.format("Logging async: %s", event));
    }
    
     public void onImportantEventInvokedWithException(@ObservesAsync @Priority(1) @Important String event) {
        log.info(String.format("Logging async with error: %s", event));
        throw new RuntimeException("Error with CDI events");
    }
}
