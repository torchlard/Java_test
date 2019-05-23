package Multi;

interface IMethod1 {
  default void m1(){
    System.out.println("m1," + i1());
  }
  default void m2(){
    System.out.println("m2");
  }

  default int i1(){
    return 2;
  }
}





