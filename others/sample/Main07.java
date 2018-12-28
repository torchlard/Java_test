package hello;

import static java.lang.System.out;
import java.lang.*;

public class Main07 {
  public static void main(String args[]){
    CB objB = new CB();
    objB.prime(100);
    objB.output();
  }
}

interface IMyPrime {
  public int p[] = new int[100];
  public default void output(){
    out.println("1");
  }
  public abstract void prime(int X);
}

class CB implements IMyPrime {
  private int index=0;
  public void prime(int X){
    for (int i=1; i<=X; i++){
      if (i==1 || i==2){
        p[index] = i;
        index++;
      } else {
        int flag = 0;
        for (int j=2; j<i; j++)
          if (i%j == 0)
            flag = 1;
        if (flag==0){
          p[index] = i;
          index++;
        }
      }
    }
  }

  public void output(){
    for (int i=0; i<index; i++)
      out.print(p[i]+" ");
    out.println();
  }
}

