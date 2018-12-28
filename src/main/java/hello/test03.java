package hello;

import java.io.*;

class test03 {
  public static void main(String[] args) throws IOException {
    File file = new File("/home/lkit/tmp/tmp2");

    if (!file.exists() || !file.canRead()){
      System.out.println("Can't read " + file);
      return;
    }

    if (file.isDirectory()){
      String[] files = file.list();
      for(String f: files){
        System.out.println(f);
      }
    } else {
      try {
        Reader ir = new InputStreamReader(new FileInputStream(file));
        BufferedReader in = new BufferedReader(ir);

        String line;
        while((line = in.readLine()) != null){
          System.out.println(line);
        }
      } catch (FileNotFoundException e) {
        System.out.println("file disappeared");
      }
    }
    
    
  }
}




