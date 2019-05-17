package Multithreading;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class ThreadA extends Thread  {
  public void run(){
    Random rand = new Random();
    int sleep = rand.nextInt(30)+1;

    try {
      Thread.sleep(sleep*100);
    } catch(InterruptedException e){
    }
    System.out.println("waited " + sleep/10.0);      
    System.out.println("thread A");
  }
}

class T1 implements Runnable {
  Turn arg;
  List<Integer> list;
  T1(Turn a, List<Integer> list){
    this.arg = a;
    this.list = list;
  }

  @Override
  public void run(){
    Random rand = new Random();
    // int sleep = rand.nextInt(30)+1;
    int sleep = 10;

    // try {
    //   Thread.sleep(sleep*100);
    // } catch(InterruptedException e){
    // }
    // System.out.println( arg + " waited " + sleep/10.0);      
    // System.out.println("thread B");    
    System.out.println(list.get(0));
    list.set(0, list.get(0) + 1);
    // arg.index++;
  }
}

// class Index {
//   public int index;
//   Index(int x){
//     index = x;
//   }
// }

public class ThreadCount {
  public static void main(String[] args) {
    Turn index = new Turn(0);
    List<Integer> list = new ArrayList<>();
    list.add(0);

    Runnable t1 = new T1(index, list);
    Runnable t2 = new T1(index, list);
    Runnable t3 = new T1(index, list);

    // Runnable task = () -> {
    //   Random rand = new Random();
    //   System.out.println(rand.nextInt(30)+1);
    // };
    
    t1.run();
    t2.run();
    t3.run();
    // Thread t6 = new Thread(new T1(6));
    // Thread t7 = new Thread(new T1(7));

    // Thread t5 = new Thread(new Runnable(){
    //   @Override
    //   public void run() {
    //     int sleep = 1000;
    //     try { Thread.sleep(1000); } 
    //     catch(InterruptedException e){}

    //     System.out.println("sleep: " + sleep/1000.0);
    //   }
    // });

    // Thread t8 = new Thread( () -> {
    //     int sleep = 1000;
    //     try { Thread.sleep(1000); } 
    //     catch(InterruptedException e){}

    //     System.out.println("sleep: " + sleep/1000.0);
    //   }
    // );

    // Thread t9 = new Thread(copy);
    // Thread t10 = new Thread(copy);

    // t9.start();
    // t10.start();

    System.out.println("Done");

  }

  static Runnable copy = () -> {
    int sleep = 1000;
    try { Thread.sleep(1000); } 
    catch(InterruptedException e){}

    System.out.println("sleep: " + sleep/1000.0);
  };

}










