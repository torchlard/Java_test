package Multi;

import java.util.ArrayList;
import java.util.List;

class Child extends Parent{

}

class Parent {

}


public class Demo {

  // @Override
  // public void m3(){
  //   System.out.println("m3");
  // }

  public static void main(String[] args) {
    // Demo demo = new Demo();
    // demo.m1();    
    // demo.m2();    
    // demo.m3();    

    // ArrayList<Object> ll = new ArrayList<Child>();
    // ArrayList<Child> ll = (ArrayList<Child>) new ArrayList<Parent>();

    // Parent p1 = new Child();
    // Child p2 = (Child) new Parent();

    Object obj = new Child();


  }
  

}







