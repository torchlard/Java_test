package Concurrency;

public class FinalReferenceDemo {
  final int[] arrays;
  private FinalReferenceDemo finalReferenceDemo;

  public FinalReferenceDemo(){
    arrays = new int[1];
    arrays[0] = 1;
  }

  public void writerOne(){
    finalReferenceDemo = new FinalReferenceDemo();
  }

  public void writerTwo(){
    arrays[0] = 2;
  }

  public void reader(){
    if (finalReferenceDemo != null){
      int temp = finalReferenceDemo.arrays[0];
    }
  }

  public static void main(String[] args) {
    FinalReferenceDemo demo = new FinalReferenceDemo();
    new Thread(() -> { demo.writerOne(); }).start();
    new Thread(() -> { demo.reader(); }).start();
    new Thread(() -> { demo.writerTwo(); }).start();

    System.out.println(demo.arrays[0]);
  }
}












