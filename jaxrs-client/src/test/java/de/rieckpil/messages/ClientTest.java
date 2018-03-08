package de.rieckpil.messages;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
  
  private Client client;
  private WebTarget tut;

  @Before
  public void init() {
    this.client = ClientBuilder.newClient();
    this.tut = this.client.target("http://localhost:8080/javaee-7-example/resources/messages");
  }

  @Test
  public void fetchMessage() {
    String string = tut.request().get(String.class);
    System.out.println(" -- " + string);
  }
}
