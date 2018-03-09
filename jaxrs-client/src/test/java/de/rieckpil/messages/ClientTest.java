package de.rieckpil.messages;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {

  private Client client;
  private WebTarget tut;
  private WebTarget processor;

  @Before
  public void init() {
    this.client = ClientBuilder.newClient();
    this.client.property(ClientProperties.CONNECT_TIMEOUT, 100);
    this.client.property(ClientProperties.READ_TIMEOUT, 500);
    this.tut = this.client.target("http://localhost:8080/messages/resources/messages");
    this.processor =
        this.client.target("http://localhost:8080/messages/resources/processors/beautification");
  }

  @Test
  public void fetchMessage() throws InterruptedException, ExecutionException {
    ExecutorService tp = Executors.newFixedThreadPool(5);
    Supplier<String> string = () -> tut.request().get(String.class);
    CompletableFuture.supplyAsync(string, tp)
      .thenApply(this::process)
      .exceptionally(this::handleExceptions)
      .thenAccept(this::consume)
      .get();
  }

  String handleExceptions(Throwable t) {
    return "sorry we are overloaded!";
  }
  
  String process(String input) {
    Response post = this.processor.request().post(Entity.text(input));
    return post.readEntity(String.class);
  }

  void consume(String message) {
    System.out.println(" received " + message);
    this.tut.request().post(Entity.text(message));
  }
}
