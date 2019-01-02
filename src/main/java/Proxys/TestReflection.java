package Proxys;

import java.io.*;
import java.lang.reflect.Method;

public class TestReflection {
  public static void main(String[] args) {
    try {
      var obj = Test.class.getDeclaredConstructor().newInstance();
      var br = new BufferedReader(new InputStreamReader(System.in) );
      var s = br.readLine();

      Method m;
      if (s.equals("sayh")){
        m = Test.class.getDeclaredMethod("sayHello");
      } else {
        m = Test.class.getDeclaredMethod("sayWorld");
      }
      m.invoke(obj);

    } catch(Exception e){
      e.printStackTrace();
    }   
  }
}

class Test {
  public void sayHello(){
    System.out.println("hello");
  }
  public void sayWorld(){
    System.out.println("world");
  }
}








