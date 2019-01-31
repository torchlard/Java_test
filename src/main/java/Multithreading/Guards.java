package Multithreading;

import java.util.Random;
import java.util.stream.Stream;

/**
 * consumer must not take existing data before producer put it
 * producer must not put data before consumer take old data
 */

public class Guards {
  public static void main(String[] args) {
    Drop drop = new Drop();
    new Thread(new Producer(drop)).start();
    new Thread(new Consumer(drop)).start();
  }
  
}

class Drop {
  private String message;
  private boolean empty = true;

  public synchronized String take(){
    while(empty){
      // acquire intrinsic lock
      // thread release lock and suspends execution
      try { wait(); } 
      catch (InterruptedException e){}
    }
    empty = true;
    notifyAll();
    return message;
  }

  public synchronized void put(String message){
    while(!empty){
      try{
        wait();
      } catch(InterruptedException e){}
    }

    empty = false;
    this.message = message;
    notifyAll();
  }
}

class Producer implements Runnable {
  private Drop drop;

  public Producer(Drop drop){
    this.drop = drop;
  }

  public void run(){
    String importantInfo[] = {
      "Mres eat oats",
      "Does eat oats",
      "Little lambs eat ivy",
      "A kid will eat ivy too",
      "Mres eat oats",
      "Does eat oats",
      "Little lambs eat ivy",
      "A kid will eat ivy too"
    };
    Random random = new Random();

    for(String i: importantInfo){
      drop.put(i);
      // try {
      //   Thread.sleep(1000);
      // } catch (InterruptedException e){}
    }
    // Stream.of(importantInfo).forEach(i -> drop.put(i));
    try {
      Thread.sleep(random.nextInt(3000));
    } catch (InterruptedException e){}

    drop.put("DONE");
  }

}

class Consumer implements Runnable {
  private Drop drop;
  Consumer (Drop drop){
    this.drop = drop;
  }

  public void run(){
    for(String msg=drop.take(); msg != "DONE"; msg = drop.take()){
      System.out.format("MESSAGE RECEIVED: %s\n", msg);
      // try {
      //   Thread.sleep(1000);
      // } catch (InterruptedException e){}
    }
    try {
      Thread.sleep(3000);
    } catch(InterruptedException e){}
  }
}






