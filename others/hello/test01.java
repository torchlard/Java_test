package hello;

import java.util.*;
import java.util.stream.*;

interface Formula {
  double calc(int a);

  default double sqrt(int a){
    return Math.sqrt(a);
  }
}

public class test01 {
  public static void main(String[] args) {

    // List<String> strings = Arrays.asList("abc","","bc","efg", "abcd", "jkl");
    // List<String> filtered = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    // for(String i: filtered){
    //   System.out.println(i);
    // }

    // Random rand = new Random();
    // rand.ints().limit(10).filter(x -> x>0).forEach(System.out::println);

    // Formula formula = new Formula(){
    //   @Override
    //   public double calc(int a){
    //     return sqrt(a*100);
    //   }
    // };

    // System.out.println( formula.calc(16));
    // System.out.println(formula.sqrt(16));

    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    // Collections.sort(names, new Comparator<String>(){
    //   @Override
    //   public int compare(String a, String b){
    //     return a.compareTo(b);
    //   }
    // });
    Collections.sort(names, (a,b) -> b.compareTo(a));

    names.stream().forEach(System.out::println);

    
    
    
    
    
    
    
    
    
  }
}

