package de.rieckpil.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample {

  public static void main(String[] args) throws InterruptedException {
    ThreadExample ex = new ThreadExample();
    // ex.runThread();
    ex.threadPool();

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
      Thread.sleep(4000000);
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
}
