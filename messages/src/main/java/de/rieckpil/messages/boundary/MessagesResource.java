package de.rieckpil.messages.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("messages")
public class MessagesResource {

  @GET
  public String getMessage() {
    return "hello duke: --" + System.currentTimeMillis();
  }

  @POST
  public void message(String message) {
    System.out.println("Server got: " + message);
  }

}
