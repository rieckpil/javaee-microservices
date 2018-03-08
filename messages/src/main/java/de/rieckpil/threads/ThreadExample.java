package de.rieckpil.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ThreadExample ex = new ThreadExample();
    // ex.runThread();
    // ex.threadPool();
    ex.backpressure();

  }

  private void runThread() throws InterruptedException {

    List<Thread> pool = new ArrayList<Thread>();

    for (int i = 0; i < 10000; i++) {
      Runnable run = this::display;
      Thread t = new Thread(run);
      pool.add(t);
      t.start();
      Thread.sleep(10);
    }
  }

  private void display() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void threadPool() throws InterruptedException {
    ExecutorService tp = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 10000; i++) {
      Runnable run = this::display;
      tp.submit(run);
      Thread.sleep(10);
    }
  }

  public String message() {
    return "Hey duke " + System.currentTimeMillis();
  }

  public void backpressure() {
    BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(2);

    ThreadPoolExecutor tp =
        new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, queue, this::onOverload);

    // new ThreadPoolExecutor.CallerRunsPolicy() || .AbortPolicy() -> throws an exception
    long start = System.currentTimeMillis();
    tp.submit(this::display);
    duration(start);
    tp.submit(this::display);
    duration(start);
    tp.submit(this::display);
    duration(start);
    tp.submit(this::display);
    duration(start);

  }

  public void duration(long start) {
    System.out.println(" -- took: " + (System.currentTimeMillis() - start));
  }

  public void onOverload(Runnable r, ThreadPoolExecutor executor) {
    System.out.println("-- runnable " + r + " executor " + executor.getActiveCount());
  }

  public void callable() throws InterruptedException, ExecutionException {
    Callable<String> messageProvider = this::message;
    ExecutorService tp = Executors.newFixedThreadPool(5);
    List<Future<String>> futures = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      Future<String> futureResult = tp.submit(messageProvider);
      futures.add(futureResult);
    }

    for (Future<String> future : futures) {
      String string = future.get();
      System.out.println(string);
    }
  }
}
