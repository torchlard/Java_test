package Multithreading;

import java.util.ArrayList;
import java.util.List;

class Resource2 {
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

class T14 implements Runnable {
  Resource2 obj;
  int index;
  // List<Integer> level;
  List<Boolean> waiting;
  Lock lock;
  
  T14 (Lock lock, List<Boolean> waiting, Resource2 obj, int index){
    this.lock = lock;
    this.waiting = waiting;
    this.obj = obj;
    this.index = index;
  }

  Boolean test_and_set(Lock lock){
    Boolean b = lock.lock;
    lock.lock = true;
    return b;
  }
  
  @Override
  public void run(){
    int N = 4;
    int count = 0;

    do {
      waiting.set(index, true);
  
      System.out.println(index + "(b): " + waiting.get(index) + " " + lock.lock);
      while(waiting.get(index) && test_and_set(lock)){}
      System.out.println(index + "(a): " + waiting.get(index) + " " + lock.lock);
      waiting.set(index, false);

      try {Thread.sleep(1000);}
      catch (InterruptedException e){}
  
      for(int i=0; i<30; i++){
        obj.addX(index);
      }
  
      int j = (index+1) % N;
      while( j != index && !waiting.get(j))
        j = (index+1) % N;
  
      if (j == index)
        lock.lock = false;
      else{
        // j enter critical
        waiting.set(j, false);
      }
  
      System.out.println(index + " ends");
      obj.getX();


    } while(true);


  }
}

class Lock {
  public Boolean lock = false;
}

public class BoundWaiting {
  public static void main(String[] args) {
    int N = 4;
    
    // List<Integer> level = new ArrayList<>();
    List<Boolean> waiting = new ArrayList<>();
    for(int i=0; i< N; i++){
      waiting.add(false);
    }
    Lock lock = new Lock();

    Resource2 res = new Resource2();

    Thread t1 = new Thread(new T14(lock, waiting, res, 0));
    Thread t2 = new Thread(new T14(lock, waiting, res, 1));
    Thread t3 = new Thread(new T14(lock, waiting, res, 2));
    Thread t4 = new Thread(new T14(lock, waiting, res, 3));

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







