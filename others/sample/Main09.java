package hello;

import static java.lang.System.out;
import java.util.Scanner;

public class Main09 {
  public static void main(String args[]) {
      int result,x,y;
      // out.println("2的 5次方=" +powerXY(2,5));
      // out.println("0的 0次方=" +powerXY(0,0));
      // out.println("2的 -2次方=" +powerXY(2,-2));
      // out.println("2的 0次方=" +powerXY(2,0));
      Scanner keyin = new Scanner(System.in);
      while(true){
        try {
          out.print("input x: ");
          x = Integer.parseInt(keyin.nextLine());
          out.print("input y: ");
          y = Integer.parseInt(keyin.nextLine());
          result = powerXY(x,y);
          break;
        } catch(NumberFormatException e){
          out.println("pls input number");
        } catch(Exception e){
          // out.println("other errors");
        }
      }
      out.println(x+" of power of "+y+" = "+result);

  }

  static int powerXY(int x,int y) throws Exception {
    int result = 1;

    try {
      if (x==0 && y==0){
        throw new CmyException("0^0 is meaningless");
      } else if (y<0) {
        throw new CmyException("power is negative, not return integer.");
      }
    }
    catch(Exception e){
      throw e;
    }

    for(int i=0; i<y; i++)
      result *=  x;
    return result;
  }
}

class CmyException extends Exception {
  public CmyException(){
    super();
  }
  public CmyException(String msg){
    super();
    out.println(msg);
  }
}








