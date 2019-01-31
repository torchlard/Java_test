package Concurrency;

public class DaemonDemo {
  public static void main(String[] args) {
    Thread daemon = new Thread(() -> {
      while(true){
        try {
          System.out.println("I am alive");
          Thread.sleep(500);
        } catch (InterruptedException e){

        } finally {
          System.out.println("finally block");
        }

      }
    });
    
    daemon.setDaemon(true);
    daemon.start();
    try {
      Thread.sleep(1000);
    } catch(InterruptedException e){}

  }
}





















