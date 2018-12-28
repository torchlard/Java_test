package hello;

import static java.lang.System.out;

public class Main06 {
  public static void main(String args[]){

    CB b = new CB();
    b.show1(3,'a');
    b.show1('a',3);
    b.show1(23);
    b.show2();

  }
}

interface IA {
  double pi = 3.14;
  public abstract void show1(int a, char b);
  public abstract void show2();
}

abstract class CA implements IA {
  public void show1(int a){
    out.println("CA: show1("+a+")");
  }
  public void show1(char a,int b){
    out.println("CA: show1("+a+","+b+")");
  }
}

class CB extends CA {
  public void show1(int b, char a){
    out.println("CB: show1("+b+","+a+")");
  }
  public void show2(){
    out.println("CB: show2()");
  }
}



