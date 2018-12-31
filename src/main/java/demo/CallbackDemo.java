package demo;

interface Cb {
  public void callbackFn();
}

class Callback {
  public void b(Cb callbackUnit){
    callbackUnit.callbackFn();
  }
}

public class CallbackDemo {

  static void disp(int[] num){
    try {
      System.out.println(num[1] / (num[1] - num[2]));
    } catch(ArithmeticException e){
      System.err.println("first exception");
    }
    System.out.println("Done");
  }

  public static void main(String[] args) {
    try {
      int[] arr = {100, 100};
      disp(arr);
    } catch(IllegalArgumentException e){
      System.err.println("second exception");
    } catch (Exception e){
      e.printStackTrace();
      System.err.println("thrid exception");
    }

        
    
    
    // var callbacker = new Callback();
    // callbacker.b(() -> {
    //   System.out.println("layer 1");
    //   callbacker.b(() -> {
    //     System.out.println("layer 2");
    //   });

    // });

  }
}










