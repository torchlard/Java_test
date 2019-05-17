package Multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.text.Format;
import java.util.Random;

public class Safelock {

  static class Friend {
    private final String name;
    private final Lock lock = new ReentrantLock();

    public Friend(String name){
      this.name = name;
    }
    public String getName(){
      return name;
    }
    
    public boolean impendingBow(Friend bower) {
      boolean myLock = false;
      boolean yourLock = false;
      try {
        myLock = lock.tryLock();
        yourLock = bower.lock.tryLock();
        System.out.println(this.name + " start lock");
      } finally {
        if (! (myLock && yourLock)){
          if (myLock)
            lock.unlock();
          if (yourLock)
            bower.lock.unlock();
          System.out.println( this.name + " unlocked");
        }
      }
      // must get both lock to continue
      return myLock && yourLock;
    }

    public void bow(Friend bower) {
      if (impendingBow(bower)){
        try {
          // actual action
          System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
          bower.bowBack(this);
        } finally {
          lock.unlock();
          bower.lock.unlock();
        }
      } else {
        System.out.format("%s: %s started bow to me " +
          "but saw that I was already bowed to him%n", this.name, bower.getName() );
      }
    }

    public void bowBack(Friend bower){
      System.out.format("%s: %s has bow back to me%n", this.name, bower.getName());
    }
  }  

  static class BowLoop implements Runnable {
    private Friend bower;
    private Friend bowee;
    public BowLoop(Friend bower, Friend bowee){
      this.bower = bower;
      this.bowee = bowee;
    }
    public void run(){
      Random random = new Random();
      while (true){
        try {
          Thread.sleep(1000);
          Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e){}
        bowee.bow(bower);
      }
    }
  }

  public static void main(String[] args) {
    final Friend alp = new Friend("alp");
    final Friend gaston = new Friend("gaston");
    new Thread(new BowLoop(alp, gaston)).start();
    new Thread(new BowLoop(gaston, alp)).start();
  }
}







