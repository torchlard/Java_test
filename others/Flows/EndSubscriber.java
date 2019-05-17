package Flows;

import java.util.*;
import java.util.concurrent.Flow.*;

public class EndSubscriber<T> implements Subscriber<T> {
  private Subscription subscription;
  public List<T> consumedElement = new LinkedList<T>();
  private String nextStr = "";

  EndSubscriber (String x){
    this.nextStr = x;
  }

  @Override
  public void onSubscribe(Subscription subscription){
    this.subscription = subscription;
    subscription.request(1);
    System.out.println("Subscribed");
  }

  @Override
  public void onNext(T item){
    System.out.println(nextStr + ": " + item);
    this.subscription.request(1);
  }

  @Override
  public void onError(Throwable t){
    t.printStackTrace();
  }

  @Override
  public void onComplete(){
    System.out.println("Done");
  }

}




