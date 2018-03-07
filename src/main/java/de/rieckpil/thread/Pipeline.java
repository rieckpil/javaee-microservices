package de.rieckpil.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class Pipeline {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Pipeline pip = new Pipeline();
    // pip.pipeline();
    // pip.combiningPipelines();
    pip.composingPipeline();
  }

  // @formatter:off
  public void pipeline() {
    CompletableFuture.supplyAsync(this::message)
        .thenApply(this::beautify)
        .thenAccept(this::consumerMessage)
        .thenRun(this::finalAction);
  }
  
  public void combiningPipelines() throws InterruptedException, ExecutionException {
    CompletableFuture<String> first = CompletableFuture.supplyAsync(this::message).thenApplyAsync(this::beautify);
    CompletableFuture<String> second = CompletableFuture.supplyAsync(this::message).thenApplyAsync(this::beautify);

    first.thenCombine(second, this::combine)
      .thenAccept(this::consumerMessage)
      .get();
  }
  
  public void composingPipeline() {
    
    CompletableFuture.supplyAsync(this::message)
      .thenCompose(this::compose)
      .thenAccept(this::consumerMessage);
    
  }
  //@formatter:on
  
  CompletionStage<String> compose(String input) {
    return CompletableFuture.supplyAsync(() -> input).thenApply(this::beautify);
  }
  
  String combine(String first, String second) {
    return first + " --  " + second;
  }

  String message() {
    return "Hey hugo -- " + System.currentTimeMillis();
  }

  String beautify(String input) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "+" + input + "+";
  }

  void consumerMessage(String message) {
    System.out.println("message: " + message.toUpperCase());
  }

  void finalAction() {
    System.out.println("-- clean up");
  }
}
