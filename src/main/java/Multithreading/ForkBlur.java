package Multithreading;

import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {
  private int[]  mSource;
  private int mStart;
  private int mLength;
  private int[] mDestinaiton;

  private int mBlurWidth = 15;
  public ForkBlur(int[] src, int start, int length, int[] dst){
    mSource = src;
    mStart = start;
    mLength = length;
    mDestinaiton = dst;
  }

  protected void computeDirectly(){
    // ...
  }

  protected static int sThreshold = 100000;
  protected void compute(){
    if (mLength < sThreshold){
      computeDirectly();
      return;
    }
    int split = mLength / 2;
    invokeAll(new ForkBlur(mSource, mStart, split, mDestinaiton),
      new ForkBlur(mSource, mStart+split, mLength-split, mDestinaiton));
  }
}










