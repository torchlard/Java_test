package Multithreading;

import java.util.concurrent.CountDownLatch;

public class ThreadLocalDemo {
  public static void main(String[] args) throws InterruptedException {
    int threads = 3;
    var countDownLatch = new CountDownLatch(threads);
    var innerclass = new InnerClass();

    for(int i=0; i<threads; i++){
      new Thread(() -> {
        for (int j=0; j<4; j++){
          // innerclass.add(String.valueOf(j));
          innerclass.add(j);
          innerclass.print();
        }
        // innerclass.set("hello world");
        innerclass.set(99);
        countDownLatch.countDown();
      }, "thread-" + i ).start();
    }
    // wait for all threads to finish
    countDownLatch.await();
  }

  private static class InnerClass {
    public void add(Integer n) {
      // get value of current thread's copy of thread-local variable
      // StringBuilder str = Counter.counter.get();
      Integer num = Counter.counter.get();
      // set own copy of thread-local variable
      // Counter.counter.set(str.append(newStr));
      Counter.counter.set(num + n);
    }

    public void print(){
      System.out.printf("Thread name: %s, ThreadLocal hashcode: %s, Instance hashcode: %s, Value: %s\n", 
        Thread.currentThread().getName(),
        Counter.counter.hashCode(),
        Counter.counter.get().hashCode(),
        Counter.counter.get().toString()
      );
    }

    public void set(Integer words) {
      // Counter.counter.set(new StringBuilder(words));
      Counter.counter.set(words);
      System.out.printf("set --- Thread name: %s, Instance hashcode: %s, Value: %s\n", 
        Thread.currentThread().getName(),
        Counter.counter.get().hashCode(),
        Counter.counter.get().toString()
      );
    }
  }

  private static class Counter {
    // declare value type of threadLocal as StringBuilder
    // private static ThreadLocal<StringBuilder> counter = new ThreadLocal<>(){
    //   @Override
    //   protected StringBuilder initialValue(){
    //     // initial value in thread local
    //     return new StringBuilder();
    //   }
    // };
    private static ThreadLocal<Integer> counter = new ThreadLocal<>(){
      @Override
      protected Integer initialValue(){
        return 1;
      }
    };
    
    
  }
  
}
















