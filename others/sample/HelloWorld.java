package hello;

import static java.lang.System.out;
import java.lang.*;
import java.util.Scanner;

public class HelloWorld {
  public static void main (String[] args){
    
    Scanner scan_input = new Scanner(System.in);
    out.print("input student number: ");
    // String raw_input = scan_input.nextLine();
    int stu_no = Integer.parseInt(scan_input.nextLine());
    out.println("");
    
    // out.println(stu_no+1);
    int array[] = new int[stu_no];
    int count[] = new int[11];
    for (int i=0; i<11; i++)
      count[i]=0;
    for (int i=0; i<stu_no; i++){
      out.print("The "+String.valueOf(i+1)+" th: ");
      array[i] = Integer.parseInt(scan_input.nextLine());
    }
    
    // get stat
    for (int elem : array) 
      count[elem/10] += 1;
    // print result
    out.println("===score distribution===");
    out.print(String.valueOf(100) + ":");
    for (int j=0; j<count[10]; j++)
      out.print("=");
    out.println("");
    
    for (int i=9; i>=0; i--){
      // out.printf("%2d~%2d: "String.valueOf(i*10)+"-"+ String.valueOf(i*10+9) +":");
      out.printf("%2d~%2d :", i*10, i*10+9);
      for (int j=0; j<count[i]; j++)
        out.print("=");
      out.println("");
    }
    
    
  }
}
