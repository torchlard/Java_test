public class thread {
  private int i;
  public int getAndIncr(){
    return i++;
  }

  public static void main(String[] args) {
    
    new Thread(() -> {
      System.out.println("hello thread");
    }).start();

    int x=1;


  }  

}












