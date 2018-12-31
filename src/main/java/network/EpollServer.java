package network;

import java.nio.channels.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

public class EpollServer {
  public static void main(String[] args) {
    try {
      var soc = ServerSocketChannel.open();
      soc.socket().bind(new InetSocketAddress("127.0.0.1", 8001));
      soc.configureBlocking(false);

      var selector = Selector.open();
      soc.register(selector, SelectionKey.OP_ACCEPT);

      var readBuffer = ByteBuffer.allocate(1024);
      var writeBuffer = ByteBuffer.allocate(128);
      writeBuffer.put("received".getBytes());
      writeBuffer.flip();

      while(true){
        selector.select();
        var it = selector.selectedKeys().iterator();
        while(it.hasNext()){
          var key = it.next();
          it.remove();

          if (key.isAcceptable()){
            System.out.println("accept");
            var socketChannel = soc.accept();
            socketChannel.configureBlocking(false);
            var connectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            connectionKey.attach(new EpollTask(socketChannel, connectionKey));
            
          } else if (key.isReadable()){
            System.out.println("readable");
            var socketChannel = (SocketChannel) key.channel();
            readBuffer.clear();
            socketChannel.read(readBuffer);
            readBuffer.flip();
            
            var conn = (EpollTask) key.attachment();
            conn.onRead(getInt(readBuffer));
            
            key.interestOps(SelectionKey.OP_WRITE);
            
          } else if (key.isWritable()){
            System.out.println("writable");
            writeBuffer.rewind();
            // var socketChannel = (SocketChannel) key.channel();

            var conn = (EpollTask) key.attachment();
            key.interestOps(SelectionKey.OP_READ);
            conn.onWrite();
          }
        }
      }
      
      
    } catch(IOException e){e.printStackTrace();}
  }

  public static int getInt(ByteBuffer buffer){
    int r = 0;
    while (buffer.hasRemaining()){
      r *= 10;
      r += buffer.get() - '0';
    }
    return r;
  }
}







