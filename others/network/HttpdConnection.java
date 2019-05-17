package network;

import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.regex.*;
import java.nio.charset.*;

/* handle get request */

public class HttpdConnection {
  static Charset charset = StandardCharsets.UTF_8;
  static Pattern httpPattern = Pattern.compile("(?s)GET /?(\\S*).*");
  SocketChannel clientSocket;
  ByteBuffer buf = ByteBuffer.allocateDirect(64*1024);
  String request;
  String response;
  FileChannel file;
  int filePosition;

  HttpdConnection(SocketChannel soc){
    this.clientSocket = soc;
  }

  void read(SelectionKey key) throws IOException {
    // get as much as possible, see if reach end of line
    if ( request == null && (clientSocket.read(buf) == -1) 
      || buf.get(buf.position()-1) == '\n'){
        processRequest(key);
    } else {
      key.interestOps(SelectionKey.OP_READ);
    }
  }

  void processRequest(SelectionKey key){
    buf.flip();
    request = charset.decode(buf).toString();
    Matcher get = httpPattern.matcher(request);

    if (get.matches()){
      request = get.group(1);
      // parse request into file path name
      if (request.endsWith("/") || request.equals(""))
        request += "index.html";
      System.out.println("Request: " + request);

      try {
        // try to get webpage file "index.html"
        file = new FileInputStream(request).getChannel();
      } catch (FileNotFoundException e) {
        response = "404 not found!";
      }
    } else
      response = "404 Bad request";

    if (response != null){
      buf.clear();
      charset.newEncoder().encode(CharBuffer.wrap(response), buf, true );
    }
    // now ready to send response
    key.interestOps(SelectionKey.OP_WRITE);
  }

  void write(SelectionKey key) throws IOException {
    // if response, then sending text response (error) 
    if (response != null){
      clientSocket.write(buf);
      if (buf.remaining() == 0)
        response = null;

    // otherwise, send file
    } else if (file != null) {
      // get remaining buffer unsent from last file.trasferTo()
      int remaining = (int) file.size() - filePosition;
      // transfer byts from file directly to network socket without copying to Java's memory space
      long sent = file.transferTo(filePosition, remaining, clientSocket);
      if (sent >= remaining || remaining <= 0){
        file.close();
        file = null;
      } else
        filePosition += sent;
    }

    if (response == null && file == null){
      // discard HttpdConnection object with it
      clientSocket.close();
      key.cancel();
    } else
      key.interestOps(SelectionKey.OP_WRITE);
  }

}


