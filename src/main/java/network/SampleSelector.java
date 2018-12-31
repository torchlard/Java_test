package network;

import java.net.*;
import java.nio.channels.*;
import java.nio.*;
import java.io.*;
import java.util.concurrent.*;

class TargetObj {
  private SocketChannel soc;
  public TargetObj(SocketChannel soc){
    this.soc = soc;
  }

  public void read(SelectionKey key){
    System.out.println("reading");
    key.interestOps(SelectionKey.OP_WRITE);
  }

  public void write(SelectionKey key) throws IOException{
    System.out.println("writing");
    key.cancel();
    soc.close();
  }
}

public class SampleSelector {
  Selector mainSelector;

  public void run(int threads) throws IOException {
    mainSelector = Selector.open();
    var soc = ServerSocketChannel.open();
    soc.configureBlocking(false);

    var addr = new InetSocketAddress(InetAddress.getLoopbackAddress() , 4501);
    soc.socket().bind(addr);
    soc.register(mainSelector, SelectionKey.OP_ACCEPT);

    var executor = Executors.newFixedThreadPool(threads);
    int count = 0;

    while(count < 5){
      while(mainSelector.select(100) == 0);
      var selectedKeys = mainSelector.selectedKeys().iterator();

      System.out.println("1");
      while(selectedKeys.hasNext()){
        var key = selectedKeys.next();
        selectedKeys.remove();
        
        if (key.isAcceptable()){
          acceptHandler(soc.accept());
        } else if (key.isReadable()){
          readHandler(key);
        } else if (key.isWritable()){
          writeHandler(key);
        } else {
          key.interestOps(0);
        }
      }
      
      count++;
    }

  }

  private void acceptHandler(SocketChannel soc) throws IOException{
    soc.configureBlocking(false);
    soc.register(mainSelector, SelectionKey.OP_READ).attach(new TargetObj(soc));
    System.out.println("accepted connection");
  }

  private void readHandler(SelectionKey key){
    var obj = (TargetObj) key.attachment();
    obj.read(key);
  }
  

  private void writeHandler(SelectionKey key) throws IOException{
    var obj = (TargetObj) key.attachment();
    obj.write(key);
  }

  public static void main(String[] args) throws IOException {
    new SampleSelector().run(1);
  }
}






