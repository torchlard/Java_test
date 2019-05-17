package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Resource3 {
  private List<Integer> list = new ArrayList<>();
  public void addX(int x){
    this.list.add(x);
  }
  public void getX(){
    for(Integer i: list){
      System.out.print(i + " ");
    }
    System.out.println();
  }
}

class T15 implements Runnable {
  Resource3 obj;
  int index;
  Lock2 lock;
  
  T15 (Lock2 lock, Resource3 obj, int index){
    this.lock = lock;
    this.obj = obj;
    this.index = index;
  }

  void acquire(){
    while(lock.lock){
      try {Thread.sleep(50);}
      catch (InterruptedException e){}
    }
    lock.lock = true;
  }

  void release(){
    lock.lock = false;
  }
  
  @Override
  public void run(){

    int count = 0;
    Random rand = new Random();

    do {
      try {Thread.sleep(rand.nextInt(100));}
      catch (InterruptedException e){}

      acquire();
  
      for(int i=0; i<30; i++){
        obj.addX(index);
      }

      // obj.getX();

      // try {Thread.sleep(1000);}
      // catch (InterruptedException e){}

      release();
  
      // System.out.println("lock " + lock.lock);
      // System.out.println(index + " ends");
      
      count++;

    } while(count < 10);


  }
}

class Lock2 {
  public Boolean lock = false;
}

public class MutexLock {
  public static void main(String[] args) {
    int N = 4;
    
    Lock2 lock = new Lock2();
    Resource3 res = new Resource3();

    Thread t1 = new Thread(new T15(lock, res, 0));
    Thread t2 = new Thread(new T15(lock, res, 1));
    Thread t3 = new Thread(new T15(lock, res, 2));
    Thread t4 = new Thread(new T15(lock, res, 3));

    // Runnable t1 = new T15(lock, res, 0);
    // Runnable t2 = new T15(lock, res, 1);
    // Runnable t3 = new T15(lock, res, 2);
    // Runnable t4 = new T15(lock, res, 3);


    t1.start();
    t2.start();
    t3.start();
    t4.start();

    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch(InterruptedException e){
    } finally {
    }
    
    res.getX();
    
  }
}







