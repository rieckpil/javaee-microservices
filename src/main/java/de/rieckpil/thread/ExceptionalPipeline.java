package de.rieckpil.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExceptionalPipeline {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExceptionalPipeline exPip = new ExceptionalPipeline();
    exPip.handle();
  }

  void handle() throws InterruptedException, ExecutionException {
    CompletableFuture.supplyAsync(this::exceptional).handle(this::handle).thenAccept(this::consume)
        .get();
  }

  String handle(String valid, Throwable ex) {
    return "-- " + valid + " -- " + ex;
  }

  String exceptional() {
    if (Math.random() < 0.5) {
      return "hugo";
    } else {
      throw new IllegalStateException("happend ._.");
    }
  }

  String transform(Throwable t) {
    return t.toString();
  }

  void consume(String message) {
    System.out.println("-- message " + message);
  }
}
