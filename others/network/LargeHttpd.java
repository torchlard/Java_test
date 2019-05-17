package network;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class LargeHttpd {
  Selector clientSelector;

  public void run(int threads) throws IOException {
    clientSelector = Selector.open();
    var ssoc = ServerSocketChannel.open();
    ssoc.configureBlocking(false);
    
    var sa = new InetSocketAddress(InetAddress.getLoopbackAddress(), 4500);
    ssoc.socket().bind(sa);
    ssoc.register(clientSelector, SelectionKey.OP_ACCEPT );

    var executor = Executors.newFixedThreadPool(threads);

    while (true){
      try {
        while(clientSelector.select(100) == 0);
        Set<SelectionKey> readySet = clientSelector.selectedKeys();
        for(Iterator<SelectionKey> it = readySet.iterator(); it.hasNext(); ){
          final var key = it.next();
          it.remove();
          if (key.isAcceptable()){
            acceptClient(ssoc);
          } else {
            key.interestOps(0);
            executor.execute(new Runnable(){
              public void run(){
                try{
                  handleClient(key);
                } catch (IOException e) { e.printStackTrace(); }
              }
            });
          }
        }
      } catch (IOException e){ e.printStackTrace();}
    }
  }

  void acceptClient( ServerSocketChannel soc ) throws IOException {
    var clientSocket = soc.accept();
    clientSocket.configureBlocking(false);
    var key = clientSocket.register(clientSelector , SelectionKey.OP_READ);
    var client = new HttpdConnection(clientSocket);
  }

  void handleClient(SelectionKey key) throws IOException {
    var client = (HttpdConnection) key.attachment();
    if (key.isReadable())
      client.read(key);
    else
      client.write(key);

    clientSelector.wakeup();
  }

  public static void main(String[] args) throws IOException{
    new LargeHttpd().run(3);
  }

}



