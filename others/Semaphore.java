package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Resource4 {
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

class T16 implements Runnable {
  Resource4 obj;
  int index;
  Signals signal;
  
  T16 (Signals signal, Resource4 obj, int index){
    this.signal = signal;
    this.obj = obj;
    this.index = index;
  }

  void waits(){
    while(signal.s <= 0){
      // try {Thread.sleep(10);}
      // catch (InterruptedException e){}
    };
    signal.s--;
  }

  void signals(){
    signal.s++ ;
  }
  
  @Override
  public void run(){

    int count = 0;
    Random rand = new Random();

    do {
      // try {Thread.sleep(rand.nextInt(2));}
      // catch (InterruptedException e){}

      waits();
  
      for(int i=0; i<30; i++){
        obj.addX(index);
      }

      // obj.getX();

      // try {Thread.sleep(1000);}
      // catch (InterruptedException e){}

      signals();
  
      // System.out.println("lock " + lock.lock);
      // System.out.println(index + " ends");
      
      count++;

    } while(count < 5);

  }
}

class Signals {
  public int s = 1;
}

public class Semaphore {
  public static void main(String[] args) {
    int N = 4;
    
    Signals signal = new Signals();
    Resource4 res = new Resource4();

    Thread t1 = new Thread(new T16(signal, res, 0));
    Thread t2 = new Thread(new T16(signal, res, 1));
    Thread t3 = new Thread(new T16(signal, res, 2));
    Thread t4 = new Thread(new T16(signal, res, 3));

    // Runnable t1 = new T16(lock, res, 0);
    // Runnable t2 = new T16(lock, res, 1);
    // Runnable t3 = new T16(lock, res, 2);
    // Runnable t4 = new T16(lock, res, 3);


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







