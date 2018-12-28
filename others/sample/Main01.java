package hello;

import static java.lang.System.out;

public class Main01 {
  public static void main(String[] args){
    
    out.println("5! = "+factorial(5));
    
  }
  
  public static int isOdd(int n){
    return n%2;
  }
  public static int factorial(int n){
    // int i=1;
    if (n==1){
      return 1;
    } else {
      return factorial(n-1)*n;
    }
  }
  
}

