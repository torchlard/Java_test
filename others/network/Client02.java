package network;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client02 {
  public static void main(String[] args) {
    try {
      var localhost = Inet4Address.getLocalHost().getHostAddress();
      var server = new Socket(localhost, 4500);
      var in = new BufferedReader(new InputStreamReader(server.getInputStream()));
      var out = new PrintWriter(server.getOutputStream(), true);

      System.out.println(in.readLine());

      var scanner = new Scanner(System.in);
      while(true) {
        System.out.println("\nEnter some text:");
        var message = scanner.nextLine();
        if (message == null || message.isEmpty()){
          break;
        }
        out.println(message);
        System.out.println(in.readLine());
      }
      
      server.close();
    } catch (UnknownHostException e){
      System.out.println("Can't find host");
    } catch (IOException e) {
      System.out.println("Error connecting to host");
    }

  }
}







