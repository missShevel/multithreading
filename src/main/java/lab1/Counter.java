package lab1;

public class Counter {
    private int value;

    public synchronized void  increment() {
        value += 1;
    }
    public synchronized void decrement() {
        value -= 1;
    }
    int getValue() {
        return this.value;
    }
}
