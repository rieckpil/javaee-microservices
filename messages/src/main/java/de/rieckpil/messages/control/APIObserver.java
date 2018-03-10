package de.rieckpil.messages.control;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class APIObserver {

    public void onImportantApiEventInvoked(@Observes @Important String event) {
        System.out.println("important event = " + event);
    }

    public void onCriticalApiEventInvoked(@Observes @Critical String event) {
        System.out.println("critical event = " + event);
    }
}
