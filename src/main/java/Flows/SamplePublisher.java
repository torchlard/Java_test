package Flows;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.Flow.*;

class SamplePublisher {
  public static void main(String[] args) {
    
    var publisher = new SubmissionPublisher<String>();
    var s1 = new EndSubscriber<String>("Got");
    var s2 = new EndSubscriber<String>("Yes");
  
    publisher.subscribe(s1);
    publisher.subscribe(s2);
    var items = List.of("1","x","2","x","3","我");
  
    items.forEach(publisher::submit);

    try{ Thread.sleep(1000);} catch(InterruptedException e){}
    publisher.close();


  }

}










