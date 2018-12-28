package hello;

import static java.lang.System.out;

public class Main11 {
  public static void main(String args[]){

    CCar obj1 = new CCar("Ford");
    CCar obj2 = new CCar("Toyota");
    // Thread obj1 = new Thread(car1);
    // Thread obj2 = new Thread(car2);
    obj1.start();
    try {
      obj1.join();
      out.println("thread 1 finished");
      obj2.start();
    } catch (InterruptedException e){}

  }
}


class CCar extends Thread {
  String manufacturer;
  public CCar(String m) {
    manufacturer = m;
  }

  // long x=0;
  public void run(){
    for(int i=1; i<=3; i++){
      // try {
      //   sleep((long) (1000*Math.random()));
      // } catch(InterruptedException e) {}
      for (int t=0; t<100000000; t++)
        ;

      out.println(manufacturer+" is running.");
    }
  }
}

