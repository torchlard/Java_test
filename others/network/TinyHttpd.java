package network;

import java.net.*;
import java.util.concurrent.*;
import java.io.*;

public class TinyHttpd {

  static class TinyHttpdConnection implements Runnable {
    Socket client;
    TinyHttpdConnection (Socket soc) throws SocketException {
      this.client = soc;
    }

    public void run(){
      try {
        var in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        var out = client.getOutputStream();
      } catch (IOException e){}
    }
  }

  public static void main(String[] args) throws IOException {
    var executor = Executors.newFixedThreadPool(3);
    var ss = new ServerSocket(4500);
    while(true){
      executor.execute(new TinyHttpdConnection(ss.accept()));
    }
  }

  
}








