package hello;

import static java.lang.System.out;
import java.util.Random;

public class Main02 {
  public static void main(String[] args){
    
    CFlyMachine arr[] = new CFlyMachine[10];
    Random rand = new Random();

    int a,b,c;
    for (int i=0; i<10; i++){
      a = rand.nextInt(50000-5000)+5000;
      b = rand.nextInt(40000-10000)+10000;
      c = rand.nextInt(4);
      arr[i] = new CFlyMachine(a,b,c);
    }
    out.println("speed  capacity  machineType");;
    for(int i=0;i<10; i++)
      arr[i].printData();
    out.println(arr[0].counter);
  }
}

class CFlyMachine {
  public int speed;
  public int carryCapacity;
  private int machineType;
  public static int counter = 0;
  
  CFlyMachine(){
    speed=0;
    carryCapacity=0;
    machineType=0;
  }
  CFlyMachine(int spd,int carry,int machine){
    speed = spd;
    carryCapacity = carry;
    machineType = machine;
    if (machine==2){
      counter += 1;
    }
  }
  
  public void printData(){
    out.printf("%-10d %-10d %d\n", speed, carryCapacity, machineType);
    if (machineType==2)
      print();
  }
  private void print(){
    out.println("one more ******");
  }
  
  
}

