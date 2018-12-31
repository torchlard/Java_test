package network;

import java.nio.channels.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

public class EpollTask {
  private SocketChannel socketChannel;
  private SelectionKey key;
  private int state;
  private int dividend;
  private int divisor;
  private int result;
  private ByteBuffer writeBuffer;

  public EpollTask(SocketChannel socChannel, SelectionKey key) {
    this.socketChannel = socChannel;
    writeBuffer = ByteBuffer.allocate(64);
    this.key = key;
  }

  public void onRead(int data){
    if (state == 0){
      dividend = data;
      System.out.println(dividend);
      state = 1;
    } else if (state == 2){
      divisor = data;
      System.out.println(divisor);

      if (divisor == 0)
        result = Integer.MAX_VALUE;
      else
        result = dividend / divisor;
      state = 3;

    } else {
      throw new RuntimeException("wrong state " + state);
    }
  }

  public void onWrite(){
    try {
      if (state == 1){
        writeBuffer.clear();
        writeBuffer.put("divident".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        state = 2;
      } else if (state == 3) {
        writeBuffer.clear();
        writeBuffer.put(String.valueOf(result).getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        socketChannel.close();
        key.cancel();
        state = 4;
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  

}













