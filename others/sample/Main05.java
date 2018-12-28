package hello;

import static java.lang.System.out;

public class Main05 {
  public static void main(String[] args){
    CCircle1 a = new CCircle1(3.0);
    CCircle2 b = new CCircle2(4.0);
    CCircle3 c = new CCircle3(5.0);
    a.computeArea();
    b.computeArea();
    c.computeArea();
    ICircle.hello(); // must explicitly call static method in interface
    a.show();
    b.show();
    c.show();

  }
}

interface ICircle {
  public static final double pi=3.14;
  public abstract void computeArea();
  public default void show(){
    out.println("pi="+pi);
  }
  public static void hello(){
    out.println("hello world");
  }
}

class CShape {
  protected double area;
  public void show(){
    out.println("area="+area);
  }
}

class CCircle1 implements ICircle {
  protected double radius;
  protected double area;
  public CCircle1 (double i) { radius=i; }
  @Override public void computeArea(){
    area = radius*radius*pi;
  }
}

class CCircle2 extends CShape implements ICircle {
  protected double radius;
  public CCircle2 (double i) { radius=i; }
  @Override public void computeArea(){
    area = radius*radius*pi;
  }
}

class CCircle3 extends CShape implements ICircle {
  protected double radius;
  public CCircle3 (double i) { radius=i; }
  @Override public void computeArea(){
    area = radius*radius*pi;
  }
  @Override public void show(){
    out.println("radius="+radius+", area="+area);
  }
}






