package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Resource4 {
  private List<Integer> list = new ArrayList<>();
  public synchronized void addX(int x){
    this.list.add(x);
  }
  public synchronized void getX(){
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
  
  T16 (Resource4 obj, int index){
    this.obj = obj;
    this.index = index;
  }

  void adds() {
    for(int i=0; i<30; i++){
      obj.addX(index);
    }

  }
  
  @Override
  public void run(){

    int count = 0;
    do {

      adds();
      count++;

    } while(count < 5);

  }
}

public class Test01 {
  public static void main(String[] args) {
    int N = 4;
    
    Resource4 res = new Resource4();

    Thread t1 = new Thread(new T16(res, 0));
    Thread t2 = new Thread(new T16(res, 1));
    Thread t3 = new Thread(new T16(res, 2));
    Thread t4 = new Thread(new T16(res, 3));

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







