package Multithreading;

public class DeadLock {

  static class Friend {
    private final String name;
    public Friend(String name){
      this.name = name;
    }
    public String getName(){
      return this.name;
    }
    public synchronized void bow(Friend bower){
      System.out.format("%s: %s has bowed to me!\n", this.name, bower.getName());
      bower.bowBack(this);
    }
    public synchronized void bowBack(Friend bower){
      System.out.format("%s: %s has bowed back to me!\n", this.name, bower.getName());
    }
  }
  
  public static void main(String[] args) {
    final Friend alphonese = new Friend("Alph");
    final Friend gaston = new Friend("Gaston");

    // deadlock for both attempting to invoke other's bowBack
    new Thread(() -> alphonese.bow(gaston)).start();
    new Thread(() -> gaston.bow(alphonese)).start();

  }
}

















