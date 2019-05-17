package demo;

import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.ScheduledFuture;


public class cornJob {

  public static void main(String[] args) {

    // ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    // ScheduledFuture<?> future = executor.schedule(
    //   () -> System.out.println("now 1s later"), 2, TimeUnit.SECONDS);

    // System.out.println("hi");
    // executor.shutdown();

    Channel ch = null;
    ScheduledFuture<?> future = ch.eventLoop().scheduleAtFixedRate(
      () -> System.out.println("hi") , 2, 5, TimeUnit.SECONDS);
    
    future.cancel(false);


  }  
  
}















