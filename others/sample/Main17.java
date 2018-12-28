package hello;

import static java.lang.System.out;

@FunctionalInterface interface IDuplicate {
  String duplicateOperation(String str);
}

interface ITrans {
  String transOperation(String str);
}

public class Main17 {
  public static void main(String args[]){

    CPrint objA = new CPrint();
    out.println("print repeat, blank in middle, change to capital");
    objA.printDupBig("HelloLambda" 
                      , (String s) -> s+", "+s
                      , (String s) -> s.toUpperCase()
                    );
    out.println(objA.printSmall("HFKsdc", (String s)->s.toLowerCase() ) );
  }
}

class CPrint {
  public void printDupBig(String s1, IDuplicate obj1, ITrans obj2){
    s1 = obj1.duplicateOperation(s1);
    s1 = obj2.transOperation(s1);
    out.println(s1);
  }
  
  public String printSmall(String s1, ITrans obj){
    s1 = obj.transOperation(s1);
    return s1;
  }
  
}

