package hello;

import static java.lang.System.out;
import java.util.*;

public class Main12 {
  public static void main(String args[]){

    LinkedList<Integer> obj1 = new LinkedList<>();
    out.println("map is empty: "+obj1.isEmpty());
    obj1.add(141);
    obj1.add(142);
    obj1.add(143);
    obj1.add(144);
    out.println("size: "+obj1.size());
    out.println("obj1: "+obj1);
    
    ListIterator<Integer> itr = obj1.listIterator();
    itr.next();
    itr.next();
    itr.remove();
    itr.previous();
    itr.remove();
    ListIterator<Integer> itr2 = obj1.listIterator();
    while(itr2.hasNext()){
      out.print( itr2.next() +" ");
    }
    out.println();
    // while(itr.hasPrevious()){
    //   out.print(itr.previous()+" ");
    // }
    // out.println();
    

  }
}
