package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Resource {
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

class T13 implements Runnable {
  int target;
  T13(int t){
    this.target = t;
  }
  @Override
  public void run(){
    try {Thread.sleep(500);}
    catch (InterruptedException e){}
    System.out.println( target + ": T13 hurray!!");
  }
}

class T12 implements Runnable {
  Resource obj;
  int index;
  List<Integer> level;
  List<Integer> waiting;
  // Turn turn;
  
  T12(List<Integer> level, List<Integer> waiting, Resource obj, int index){
    this.level = level;
    this.waiting = waiting;
    this.obj = obj;
    this.index = index;
  }
  
  @Override
  public void run(){
    int N = 4;
    int count = 0;
    // int other = (index + 1)%2 ;
   
    // (new Thread(new T13(target+2))).start();
    // do {
      // System.out.println("start " + index);
      for (int l=0; l<N-1; ++l){

        level.set(index, l);
        waiting.set(l, index);
        final int kk = l;
        while(waiting.get(l) == index && 
          IntStream.range(0,N-1).filter(i -> i != index).anyMatch(i -> level.get(i) >= kk) ){
            // busy waiting
          }
      }
      // System.out.println(index + ": before " + flags.get(other) +", "+ turn.turn);
      // while (!flags.get(index) && turn.turn != index){}
      // System.out.println(index + ": after " + flags.get(other) +", "+ turn.turn);

      for(int i=0; i<30; i++){
        obj.addX(index);
      }

      // try {Thread.sleep(200); }
      // catch (InterruptedException e) {}
      
      // flags.set(index, false);
      level.set(index, -1);
      // count++;
    // } while(count == 0);
  }
}

class Turn {
  public int turn;
  Turn(int x){
    turn = x;
  }
}

public class SessionControl {
  public static void main(String[] args) {
    int N = 4;
    
    List<Integer> level = new ArrayList<>();
    List<Integer> waiting = new ArrayList<>();
    for(int i=0; i< N; i++){
      level.add(-1);
      waiting.add(-1);
    }
    // Turn turn = new Turn(0);

    Resource res = new Resource();

    Thread t1 = new Thread(new T12(level, waiting, res, 0));
    Thread t2 = new Thread(new T12(level, waiting, res, 1));
    Thread t3 = new Thread(new T12(level, waiting, res, 2));
    Thread t4 = new Thread(new T12(level, waiting, res, 3));

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

    // Runnable t1 = new T12(res, 0);
    // Runnable t2 = new T12(res, 30);
    // Runnable t3 = new T12(res, 60);
    // Runnable t4 = new T12(res, 90);
    // t1.run();
    // t2.run();
    // t3.run();
    // t4.run();

    
    res.getX();
    
  }
}










