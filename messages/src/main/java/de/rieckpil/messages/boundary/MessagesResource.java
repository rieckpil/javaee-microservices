package de.rieckpil.messages.boundary;

import de.rieckpil.messages.control.Critical;
import de.rieckpil.messages.control.Important;

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
    importantEvent.fireAsync("IMPROTANT ASNYC: DUKE was being invoked!");
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

}
