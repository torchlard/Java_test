package Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {
  public static void main(String[] args) {
    ExecutorService service = Executors.newSingleThreadExecutor();
    Future<String> future = service.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        return "run callable";
      }
    });

    try {
      String result = future.get();
      System.out.println(result);
    } catch(InterruptedException e){

    } catch(ExecutionException e){}
  }
}











