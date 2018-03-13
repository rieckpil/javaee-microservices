package de.rieckpil.messages.control;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.interceptor.Interceptor;

public class APIObserver {

    public void onImportantApiEventInvoked(@Observes @Priority(Interceptor.Priority.APPLICATION - 1) @Important String event) {
        System.out.println("1. important event = " + event);
    }

    public void onImportantApiEventInvokedTwo(@Observes @Priority(Interceptor.Priority.APPLICATION - 2) @Important String event) {
        System.out.println("2. important event = " + event);
    }

    public void onImportantApiEventInvokedThree(@Observes @Priority(Interceptor.Priority.APPLICATION - 3) @Important String event) {
        System.out.println("3. important event = " + event);
    }

    public void onCriticalApiEventInvoked(@Observes @Priority(Interceptor.Priority.APPLICATION - 1) @Critical String event) {
        System.out.println("critical event = " + event);
    }
}
