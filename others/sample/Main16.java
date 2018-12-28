package hello;

import static java.lang.System.out;

public class Main16 {
  public static void main(String args[]){
    
    CB objX = new CB();
    objX.derive();

  }
}

class CA {
  public int varCA;
  public CA() {varCA=1;}
  public void show1(){
    out.println("CA show1(),var="+varCA);
  }
  public void show2(){
    out.println("CA show2(),var="+varCA);
  }
}

class CB {
  public int varCB;
  public CB() {varCB=2;}
  public void derive(){
    // anonymous class, extends from CA, override show1, add show3
    // CA obj1 = new CA(){...}
    ( new CA(){
      // public void show1(){
      //   out.println("derive show1()");
      //   out.println("varCA: "+varCA);
      // }
      public void show3(){
        out.println("derive show3()");
        out.println("varCA: "+varCA);
      }
    } ).show3();
    // obj1.show2();
    // obj1.varCA = 50;
    // obj1.show1();
  }
}

class CC {
  public int var1 = 5;
  public void derive(){
    int localVar1 = 100;
    final int localVar2 = 100;
    ( new Object(){
      public void show(){
        out.println("this is anonymous show()");
      }
    }).show();
  }
}





