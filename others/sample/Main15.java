package hello;

import static java.lang.System.out;

public class Main15 {
  public static void main(String args[]){
    OuterClass outY = new OuterClass(10);
    outY.changeInner(45);
    outY.showInner();
    // OuterClass.InnerClass innerX = outY.new InnerClass(20);
    // innerX.innerShow("17th");
    
    out.println(xx);
    // special way to bypass static restriction inside main
    Main15 obj02 = new Main15();
    obj02.spc.show();
  }
  static int xx = 5; // must be static
  
  public SpecialClass spc;
  public Main15(){
    spc = new SpecialClass();
  }
  class SpecialClass {
    int innerVar;
    public SpecialClass(){
      innerVar = 53;
    }
    public void show(){
      out.println("innerVar: "+innerVar);
    }
  }
  
}

class OuterClass {
  // shown as private data member
  private class InnerClass {
    private int innerVar;
    public InnerClass() {}
      public InnerClass(int i) {innerVar=i;}
      public void innerShow(String str){
        out.println(str+", outVar="+outVar);
        out.println(str+", innerVar="+innerVar);
      }
    }
    
  private int outVar;
  public InnerClass obj;
  public OuterClass() {
    obj = new InnerClass();
  }
  public OuterClass(int i) {
    obj = new InnerClass();
    outVar=i;
  }
  
  public void changeInner(int j){
    obj.innerVar = j;
  }    

  public void showInner(){
    obj.innerShow("abc");
  }
}


