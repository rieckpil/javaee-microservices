package de.rieckpil.messages.boundary;

import de.rieckpil.messages.control.Critical;
import de.rieckpil.messages.control.Important;
import java.util.concurrent.CompletionStage;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("messages")
@Stateless
public class MessagesResource {

    @Inject
    @Important
    Event<String> importantEvent;

    @Inject
    @Critical
    Event<String> criticalEvent;

    @GET
    public String getMessage() {
        importantEvent.fire("IMPROTANT SYNC: DUKE was being invoked!");
        CompletionStage<String> fireAsync = importantEvent.fireAsync("IMPROTANT ASNYC: DUKE was being invoked!");
        fireAsync.whenComplete(this::handleWhenComplete);
        fireAsync.handle(this::handleException);
        return "hello duke: --" + System.currentTimeMillis();
    }

    @GET
    @Path("critical")
    public String getCriticalMessage() {
        criticalEvent.fire("CRITICAL: DUKE was being invoked!");
        return "hello duke: --" + System.currentTimeMillis();
    }

    @POST
    public void message(String message) {
        System.out.println("Server got: " + message);
    }
    
    private void handleWhenComplete(String input, Throwable t) {
        System.out.println("Finished handling CDI events: " + input.toString());
    }
    
    private Object handleException(String input, Throwable t) {
        System.out.println("Error when firing CDI events: " + t.getMessage());
        return null;
    }

}
