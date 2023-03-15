package lab1;

public class Counter {
    private int value;

    public synchronized void increment() throws InterruptedException {
        while (value > 100000) wait();
        value += 1;
        notifyAll();

    }
    public synchronized void decrement() throws InterruptedException {
        while (value < -100000) wait();
        value -= 1;
        notifyAll();

    }
    int getValue() {
        return this.value;
    }
}
