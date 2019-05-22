package Concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

  private static Exchanger<String> exchanger = new Exchanger<>();
  
  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(2);

    service.execute(() -> {
      try {
        String girl = exchanger.exchange("i also like you");
        System.out.println("boy said: " + girl);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
    });

    service.execute(() -> {
      try {
        System.out.println("girl walking out of classroom");
        String boy = exchanger.exchange("I like you");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("girl said: " + boy);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    });
    
  }
}












