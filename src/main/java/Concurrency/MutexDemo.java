package Concurrency;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Mutex implements Lock, java.io.Serializable {
  public static class Sync extends AbstractQueuedSynchronizer {
    // whether locked
    protected boolean isHeldExclusively(){
      return getState() == 1;
    }
    public boolean tryAcquire(int acquires){
      assert acquires == 1;
      if (compareAndSetState(0,1)){
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
      }
      return false;
    }

    // release lock by setting state to 0
    protected boolean tryRelease(int releases){
      assert releases == 1;
      if (getState() == 0) throw new IllegalMonitorStateException();
      setExclusiveOwnerThread(null);
      setState(0);
      return true;
    }

    Condition newCondition(){
      return new ConditionObject();
    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      setState(0);
    }
  }

  private final Sync sync = new Sync();

  public void lock(){
    sync.acquire(1);
  }
  public boolean tryLock(){
    return sync.tryAcquire(1);
  }
  public void unlock(){
    sync.release(0);
  }
  public Condition newCondition(){
    return sync.newCondition();
  }
  public boolean isLocked(){
    return sync.isHeldExclusively();
  }
  public boolean hasQueuedThreads(){
    return sync.hasQueuedThreads();
  }
  public void lockInterruptibly() throws InterruptedException {
    sync.acquireInterruptibly(1);
  }
  public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException{
    return sync.tryAcquireNanos(1, unit.toNanos(timeout));
  }
  
}

public class MutexDemo {
  private static Mutex mutex = new Mutex();

  public static void main(String[] args) {
    for (int i=0; i<10; i++){
      Thread thread = new Thread(() -> {
        mutex.lock();
        try {
          Thread.sleep(3000);
        } catch(InterruptedException e){

        } finally {
          mutex.unlock();
        }
      });
      thread.start();
    }  
  }
}






















