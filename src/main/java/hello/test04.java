package hello;

import java.io.*;
import java.util.*;

// import hello.Token.TokenType;

/** Reader = target, System.in = Adaptee, InputStreamReader = Adapter */

interface TokenStream {
  public Token getToken() throws IOException;
  public void consumeToken();
}

class Token {
  public enum TokenType {
    LPAR, NONE
  }
  public TokenType tokenType;
  public Object value;

  public Token(TokenType tt, Object v){
    this.tokenType = tt;
    this.value = v;
  }
}

class YourTokenStream implements TokenStream {
  ArrayList<Token> tkList = new ArrayList<>();
  
  public YourTokenStream(InputStream in){
    var chars = "";
    var reader = new InputStreamReader(in);
    char ch;
    int i;
    Token.TokenType tk;

    try {
      while((i = reader.read()) != -1){
        ch = (char) i;
        chars += ch;
      }
    } catch (IOException e){
      e.printStackTrace();
    }

    if (chars == "None")
      tk = Token.TokenType.NONE;
    else
      tk = Token.TokenType.LPAR;

    tkList.add(new Token(Token.TokenType.LPAR, "a"));
    tkList.add(new Token(Token.TokenType.LPAR, "bc"));
    tkList.add(new Token(tk, chars));
    // tkList.add(new Token(Token.TokenType.NONE, "b"));
    return;
  }
  
  public Token getToken() throws IOException {
    var end = new Token(Token.TokenType.NONE, "a");
    if (tkList.size() == 0)
      return end;
    return tkList.get(0);
  }

  public void consumeToken(){
    var tk = tkList.remove(0);
    System.out.println("Consumed token: " + tk.value);
  }
}

class test04 {
  public static void main(String[] args) throws IOException {
    // // var buf = new byte[512];
    // var chr = new char[512];
    // System.out.println("enter you name pls:");    
    // int n = 0;
    // try {
    //   // n = System.in.read(buf);
    //   // n = new InputStreamReader(System.in).read(chr);
    //   n = new InputStreamReader(System.in).read(chr);
    // } catch(IOException e){
    //   e.printStackTrace();
    // }
    // System.out.print("hello " + chr[0] + chr[1] +"0");
    // // System.out.write(buf, 0,n);
    
    System.out.println("input:");
    TokenStream ts = new YourTokenStream(System.in);
    while(ts.getToken().tokenType != Token.TokenType.NONE ) {ts.consumeToken();}

  }
}





