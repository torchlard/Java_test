package hello;

import static java.lang.System.out;

public class Main04 {
  public static void main(String[] args){
    CTriangle a = new CTriangle();
    CRect b = new CRect();
    CPentagon c = new CPentagon();
    out.print(a.toString()); a.angle();
    out.print(b.toString()); b.angle();
    out.print(c.toString()); c.angle();
    

  }
}

abstract class CShape {
  public int number;
  public int totalAngle;
  public abstract void angle();
  public String toString(){
    return "shape "+number+" each angle has ";
  }
}

class CTriangle extends CShape {
    CTriangle(){
      number = 3;
      totalAngle = (number-2)*180;
    }
    @Override public void angle(){
      out.println(totalAngle / number);
    }
}

class CRect extends CShape {
    CRect(){
      number = 4;
      totalAngle = (number-2)*180;
    }
    @Override public void angle(){
      out.println(totalAngle / number);
    }
}

class CPentagon extends CShape {
    CPentagon(){
      number = 5;
      totalAngle = (number-2)*180;
    }
    @Override public void angle(){
      out.println(totalAngle / number);
    }
}





