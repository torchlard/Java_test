package hello;
import static java.lang.System.out;
import java.util.*;

@FunctionalInterface
interface IFIABS {
  int abs(int a);
}

public class Main18 {

  public static int sum=0;
  public static void main(String args[]){
    Main18 objA = new Main18();
    objA.test();

    HashSet<Integer> hs = new HashSet<>();
    hs.add(3); hs.add(4);
    hs.add(6); hs.add(2);
    hs.add(9); hs.add(8);
    out.print("HashSet content: ");
    hs.forEach(obj -> out.print(obj+1+" "));
    out.println();
  }

  public void test(){
    IFIABS obj1 = (int i) -> {
      if (i<0) return (-1)*i;
      return i;
    };
    out.println(obj1.abs(-3));

    IFIABS obj2 = (int i)-> CABS.myabs(i);
    out.println(obj2.abs(-4));

    IFIABS obj3 = CABS::myabs;
    out.println(obj3.abs(-5));
  }

}

class CABS {
  public static int myabs(int x){
    if (x<0) return x*(-1);
    return x;
  }
}















