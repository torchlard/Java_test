package hello;

import static java.lang.System.out;
import java.lang.*;
import java.io.*;

public class Main10 {
  public static void main(String args[]) throws IOException, FileNotFoundException {

    // read
    char cbuf[] = new char[1256];
    FileReader fr = new FileReader("/home/lkit/tmp/tmp.json");

    String str1;
    int num;
    while((num = fr.read(cbuf)) != -1){
      str1 = new String(cbuf,0,num);
      out.println("total "+num+" words");
      out.println("file content:");
      out.println(str1);
    }
    fr.close();

    // write
    String str2 = "Fibonacci sequence:";
    char endCh[] = {'C','o','n','t','i','n'};
    int numF;
    FileWriter fw = new FileWriter("/home/lkit/tmp/666.txt");

    fw.write(str2);
    fw.write('\r'); fw.write('\n');
    for(int i=1; i<10; i++){
      numF = Fib(i);
      fw.write(numF+" ");
    }
    fw.write(endCh);
    fw.close();
  }
  public static int Fib(int n){
    if((n==1)||(n==2))
      return n;
    else
      return Fib(n-1)+Fib(n-2);
  }

}