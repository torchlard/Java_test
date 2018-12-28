package hello;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

// class Person {
//   String firstName;
//   String lastName;

//   Person(){}
//   Person(String firstName, String lastName){
//     this.firstName = firstName;
//     this.lastName = lastName;
//   }
// }

// interface PersonFactory<P extends Person>{
//   P create(String firstName, String lastName);
// }

class Task implements Callable<Integer>{
  @Override
  public Integer call() throws Exception {
    System.out.println("sub-thread calculating...");
    Thread.sleep(3000);

    return IntStream.range(0,100).reduce(0, (x,y) -> x+y);
  }
}


public class test02 {
  public static void main(String[] args) throws InterruptedException {
    
    // PersonFactory<Person> personFactory = Person::new;
    // Person person = personFactory.create("Peter", "Parker");
    // Person p2 = personFactory.create("John", "Pejlk");
    
    // System.out.println(person.firstName);    
    // System.out.println(p2.lastName);    

    // ExecutorService executor = Executors.newCachedThreadPool();
    // Task task = new Task();
    // Future<Integer> result = executor.submit(task);
    // executor.shutdown();
    
    // try {
    //   Thread.sleep(1000);
    // } catch(InterruptedException el){
    //   el.printStackTrace();
    // }

    // System.out.println("main thread executing");

    // try {
    //   System.out.println("task result: " + result.get());
    // } catch(Exception e){
    //   e.printStackTrace();
    // }

    // System.out.println("All tasks finished");

    long l = System.currentTimeMillis();
    CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("executing time-consuming task ...");
      timeCon();
      return 100;
    });

    completableFuture.whenComplete((result, e) -> {
      System.out.println("result: " + result);
    });

    System.out.println("time spend: " + (System.currentTimeMillis()-l) + "ms" );
    new CountDownLatch(1).await();
  }

  static void timeCon(){
    try{
      Thread.sleep(3000);
    } catch(Exception e){
      e.printStackTrace();
    }

  }

  
  
  
  
  
}

