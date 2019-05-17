package network;

import java.io.*;
import java.net.*;

public class Server02 {
  public static void main(String[] args) throws Exception {
    System.out.println("Server starts running");
    int num = 0;
    try (ServerSocket listener = new ServerSocket(4500)){
      while(true){
        new Capital(listener.accept(), num++).start();
      }
    }
  }

  private static class Capital extends Thread {
    private Socket socket;
    private int clientNum;

    public Capital(Socket soc, int clientNum){
      this.socket = soc;
      this.clientNum = clientNum;
      System.out.println("new client "+ clientNum +" connected");
    }

    public void run(){
      try {
        var in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        var out = new PrintWriter(this.socket.getOutputStream(), true);
        
        out.println("hello, client " + this.clientNum);
        while(true){
          var res = in.readLine();
          if (res == null || res.isEmpty()){
            break;
          }
          System.out.println("Get msg from client " + this.clientNum + ": " + res);
          out.println("received msg");
        }
        
      } catch(IOException e){
        e.printStackTrace();
      } finally {
        try { this.socket.close(); } catch(IOException e){}
        System.out.println("system closed");
      }
    }
  }
}


















