package Enum;

interface PropDefinition {
  int LEFT = 1;
  int RIGHT = 2;
  int UP = 3;
  int DOWN = 4;
}

enum Propagation {
  LEFT(PropDefinition.LEFT),
  RIGHT(PropDefinition.RIGHT),
  UP(PropDefinition.UP),
  DOWN(PropDefinition.DOWN);

  private final int value;

  Propagation(int value){
    this.value = value;
  }

  public int value(){
    return this.value;
  }

}


public class Demo {
  public static void main(String[] args) {

    System.out.println(Propagation.LEFT.value());
    
    
  }
}






















