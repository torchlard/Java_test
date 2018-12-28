package hello;

import static java.lang.System.out;

public class Main03 {
  public static void main(String[] args){
    // if master type with child instance, cannot run new method from child
    CA objA = new CB();
    objA.show();
    objA.show("str");
    // objA.show(1.1);
  }
}

class CA {
  protected void show(){
    out.println("now running master");
  }
  public void show(String a){
    out.println("string: "+a);
  }
}

class CB extends CA {
  // @Override for human read and compiler
  @Override public void show(){
    out.println("now running child");
  }
  public void show(int a){
    out.println("int val: "+a);
  }
  public void show(double a){
    out.println("double: "+a);
    super.show();
  }
}

