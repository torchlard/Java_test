import static java.lang.System.out;
import java.lang.Thread;

class Horse extends Thread {
  public void run(){
    // int h=0;
    // for(int i=0; i<30; i++){
    //   h++;
    //   out.println(getName()+":"+h);
    // }

    try {
      sleep(2000);
      out.println(getName()+" to terminal");
    } catch (InterruptedException e){
      out.println(getName() + " interrupted");
    }
  }
}

class Wizard extends Thread {
  public void run(){
    thunder();
  }
  
  private synchronized void thunder() {
    out.println("Thunder!!");
    try {
      sleep(1000);
    } catch (InterruptedException e){
      e.printStackTrace();
    }
    out.println("End");
  }
}

class HorseRunnable implements Runnable {
  public void run(){
    int h=0;
    for(int i=0; i<50; i++){
      h++;
      out.println(h);
    }
  }
}

public class ThreadName {
  public static void main(String[] args){
    out.println(" ---------------- ");
    // Thread thr = Thread.currentThread();

    // out.println("current thread name: "+thr.getName());
    // thr.setName("DEMO");
    // out.println("updated name: "+thr.getName());
    
    // int h1 = 0;
    // Horse h2 = new Horse();
    // h2.start();
    // for(int i=0; i<50; i++){
    //   h1++;
    //   out.println("H1: "+h1);
    // }

    // Horse h1 = new Horse();
    // Horse h2 = new Horse();
    // Horse h3 = new Horse();
    // h1.setName("h1");
    // h2.setName("h2");
    // h3.setName("h3");
    // h1.start();
    // h2.start();
    // h3.start();

    // try {
    //   h1.join();
    //   h2.join();
    //   h3.join();
    // } catch (InterruptedException e){
    //   out.println("interr.");
    // }
    // out.println("main ends");

    Wizard wizard = new Wizard();
    Thread thr1 = new Thread(wizard);
    thr1.start();
    Thread thr2 = new Thread(wizard);
    thr2.start();

  }
}




