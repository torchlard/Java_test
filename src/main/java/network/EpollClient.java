package network;

import java.nio.channels.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

public class EpollClient {
  public static void main(String[] args) {
    try {
      var socketChannel = SocketChannel.open();
      socketChannel.connect(new InetSocketAddress("127.0.0.1", 8001));

      var writeBuffer = ByteBuffer.allocate(32);
      var readBuffer = ByteBuffer.allocate(32);
      var buf = new byte[32];
      var r = new Random();

      int d = r.nextInt(1000);
      System.out.println("first int: " + d);
      writeBuffer.put(String.valueOf(d).getBytes());
      writeBuffer.flip();
      socketChannel.write(writeBuffer);

      socketChannel.read(readBuffer);
      readBuffer.flip();
      System.out.println("get int: " + new String(readBuffer.array()));
      
      try {Thread.sleep(3000);} catch(InterruptedException e){
        // e.printStackTrace();
      }

      writeBuffer.clear();
      d = r.nextInt(10);
      System.out.println("second int: " + d);
      writeBuffer.put(String.valueOf(d).getBytes());
      writeBuffer.flip();
      socketChannel.write(writeBuffer);

      readBuffer.clear();
      socketChannel.read(readBuffer);
      readBuffer.flip();
      readBuffer.get(buf, 0, readBuffer.remaining());
      System.out.println(new String(buf));

      socketChannel.close();

    } catch (IOException e){ e.printStackTrace(); }
  }
}












