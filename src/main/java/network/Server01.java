package network;

import java.io.IOException;
import java.net.*;
import java.io.*;


public class Server01 {
  public static void main(String[] args) {
    try(ServerSocket listener = new ServerSocket(4500)) {
      System.out.println("Data server is running");
      while(true){
        try(Socket socket = listener.accept()){
          System.out.println("connection accepted");
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          out.println("fdsafdsa");
          out.println("12");

          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()) );
          String res = in.readLine();
          System.out.println(res);
        }
        return;
      }
    } catch(IOException e){

    } 
  }
}











