package de.rieckpil.messages.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("messages")
public class MessagesResource {

  @GET
  public String getMessage() {
    return "hello duke: --" + System.currentTimeMillis();
  }

}
