package Multithreading;

final public class ImmutableRGB {
  final private int red;
  final private int green;
  final private int blue;
  final private String name;

  private void check(int red, int green, int blue){
    if (red<0 || red > 255 || green<0 || green>255 || blue<0 || blue>255)
      throw new IllegalArgumentException(); 
  }

  public ImmutableRGB(int red, int green, int blue, String name){
    check(red, green, blue);
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getRGB(){
    return ((red<<16) | (green<<8) | blue);
  }

  public ImmutableRGB invert(){
    return new ImmutableRGB(255-red, 255-green, 255-blue, "inverse of " + name);
  }

  public static void main(String[] args) {
    
  }
}









