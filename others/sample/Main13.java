package hello;

import static java.lang.System.out;
import java.util.*;

public class Main13 {
  public static void main(String args[]){
    
    LinkedList<CStu> obj1 = new LinkedList<>();
    obj1.add(new CStu(147,"mandy",12));
    obj1.add(new CStu(157,"lau",52));
    obj1.add(new CStu(167,"pak",32));
    
    CComp objComp = new CComp();
    Collections.sort(obj1, objComp);
    
    ListIterator<CStu> itr = obj1.listIterator();
    while(itr.hasNext()){
      itr.next().showData();
    }
    
  }
}

class CComp implements Comparator<CStu> {
  public int compare(CStu o1, CStu o2){
    // if (o1 instanceof CStu && o2 instanceof CStu){
      if (o1.score < o2.score )
        return -1;
      else if (o1.score == o2.score )
        return 0;
      else if (o1.score > o2.score )
        return 1;
    // }
    return 0;
  }
}

class CStu {
  int id;
  String name;
  int score;
  public CStu(int i,String j, int k) {
    id=i; name=j; score=k;
  }
  public void showData(){
    out.print("id: "+id);
    out.print("\tname: "+name);
    out.println("\tscore: "+score);
  }
}

