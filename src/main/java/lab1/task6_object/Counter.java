package lab1.task6_object;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int value;
    ReentrantLock locker = new ReentrantLock();

    public void increment() throws InterruptedException {
        locker.lock();
        value += 1;
        locker.unlock();


    }
    public void decrement() throws InterruptedException {
        locker.lock();
        value -= 1;
        locker.unlock();


    }
    public int getValue() {
        return this.value;
    }
}
