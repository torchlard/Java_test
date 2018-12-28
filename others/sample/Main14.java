package hello;

import static java.lang.System.out;
import java.util.*;
import java.util.Scanner;

public class Main14 {
  public static void main(String[] args){
    
    int findNum, location;
    LinkedList<Integer> ts1 = new LinkedList<>();
    int workArr[] = {43,23,67,27,39,37,57,26,14};
    for (int i: workArr)
      ts1.add(i);
    
    // Collections.sort(ts1);
    Comp cmp = new Comp();
    Collections.sort(ts1, cmp);
    
    Scanner keyin = new Scanner(System.in);
    out.print("Pls enter value you want to find: ");
    findNum = Integer.parseInt(keyin.nextLine());
    if(ts1.contains(findNum))
      out.println("the number is at "+ts1.indexOf(findNum));
    else 
      out.println("does not exist.");
    
    // ListIterator<Integer> itr = ts1.listIterator();
    // while (itr.hasNext()){
    //   if (itr.next().equals(findNum) ){
    //     out.println("exists!");
    //     break;
    //   }
    // }
      
    out.println(ts1);  
    
  }
}

// sort by descending
class Comp implements Comparator<Integer> {
  public int compare(Integer o1, Integer o2){
    if (o1>o2)
      return -1;
    else if (o1==o2)
      return 0;
    else if (o1<o2)
      return 1;
    return 0;
  }
}

