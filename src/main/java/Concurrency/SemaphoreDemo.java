package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

  private static Semaphore semaphore = new Semaphore(5);
  
  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(10);
    for(int i=0; i<10; i++){
      service.execute(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + " prepare to get pen");
          semaphore.acquire();
          System.out.println(Thread.currentThread().getName() + " got pen");
          System.out.println(Thread.currentThread().getName() + " filling in form...");
          TimeUnit.SECONDS.sleep(3);
          semaphore.release();
          System.out.println(Thread.currentThread().getName() + " returned pen");
        } catch(InterruptedException e){}
      });

    }
    service.shutdown();
  }
}













