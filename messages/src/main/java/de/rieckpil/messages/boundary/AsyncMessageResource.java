package de.rieckpil.messages.boundary;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("async-messages")
public class AsyncMessageResource {

  @Resource
  ManagedExecutorService mes;

  @GET
  public void doSomeWork(@Suspended AsyncResponse response) {
    CompletableFuture
      .supplyAsync(this::doWork, mes)
      .thenAccept(response::resume);
  }

  String doWork() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "work done!";
  }

}
