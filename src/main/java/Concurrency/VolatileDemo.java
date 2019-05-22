package Concurrency;

public class VolatileDemo {
  // ensure write to isOver will be updated by Thread
  private static volatile boolean isOver = false;

  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      while(!isOver);
    });

    thread.start();
    try {
      Thread.sleep(500);
    } catch(InterruptedException e){}

    isOver = true;
  }
}










