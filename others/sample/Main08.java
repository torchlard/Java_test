package hello;

import static java.lang.System.out;
import java.util.Scanner;

public class Main08 {
  public static void main(String args[]){
    Scanner keyinput = new Scanner(System.in);
    out.print("enter size(6-48): ");
    int lottoSize = 0;

    try {
      lottoSize = Integer.parseInt(keyinput.nextLine());
      if (lottoSize>48)
        throw new CmyException1("too large!");
      else if (lottoSize<6)
        throw new CmyException2("too small!");
    } catch (NumberFormatException e) {
      out.println("size is not a number. set to 6.");
      lottoSize = 6;
    }
    catch (CmyException1 | CmyException2 e) {
      out.println("reset size to 6.");
      lottoSize = 6;
    }
    catch (Exception e) {
      out.println("other exceptions");
      lottoSize = 12;
    }
    finally {
      out.println("winning number is "+lottoSize);
    }

    int lottoArr[] = new int[lottoSize];
    out.println("finished");
  }
}

class CmyException1 extends Exception {
  public CmyException1(){
    super();
  }
  public CmyException1(String msg){
    super();
    out.println(msg);
  }
}

class CmyException2 extends Exception {
  public CmyException2(){
    super();
  }
  public CmyException2(String msg){
    super();
    out.println(msg);
  }
}
