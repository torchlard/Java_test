package Multithreading;

public class Yields {
  public static void main(String[] args) {
    Thread t1 = new MyThread();
    Thread t2 = new MyThread();
    t1.start();
    t2.start();
        
    System.out.println("main thread");
  }
} 

class MyThread extends Thread {
  public void run(){

    System.out.println("thread start");

    if (Thread.interrupted()){
      System.out.println("interrupted Oh shit");
    }

    try{ sleep(2000);}
    catch (InterruptedException e ){}
    System.out.println("I'm t1");

  }
}









