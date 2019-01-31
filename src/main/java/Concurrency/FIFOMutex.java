package Concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * mutual exclusion with guaranteed FIFO scheduling for critical session access
 */

public class FIFOMutex {
  private final AtomicBoolean locked = new AtomicBoolean(false);
  private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

  public void lock(){
    boolean wasInterrupted = false;
    Thread current = Thread.currentThread();
    waiters.add(current);

    while(waiters.peek() != current || !locked.compareAndSet(false, true)){
      // if current thread not first in FIFO || lock is currently not false
      // wait for other thread to unpark you
      LockSupport.park(this);
      if (Thread.interrupted())
        wasInterrupted = true;
    }

    waiters.remove();
    if (wasInterrupted)
      current.interrupt();
  }

  public void unlock(){
    locked.set(false);
    LockSupport.unpark(waiters.peek());
  }
}







