package network;

import java.net.*;
// import java.util.*;
import java.io.*;

public class Client01 {
  public static void main(String[] args) {
    try {

      var localhost = Inet4Address.getLocalHost().getHostAddress();
      Socket server = new Socket(localhost, 4500);
      // InputStream in = server.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
      PrintWriter out = new PrintWriter(server.getOutputStream(), true);

      // DataInputStream din = new DataInputStream(in);
      // int d = din.readInt();      
      String response = in.readLine();
      String r2 = in.readLine();
      int res = Integer.parseInt(r2);
      System.out.println("receive int: " + response);
      System.out.println("receive int: " + (res+1));

      out.println("response from client");
      
      server.close();
    } catch (UnknownHostException e){
      System.out.println("Can't find host");
    } catch (IOException e) {
      System.out.println("Error connecting to host");
    }

  }
}







