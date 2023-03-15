package lab1;

public class Counter {
    private int value;

    void increment() {
        value += 1;
    }
    void decrement() {
        value -= 1;
    }
    int getValue() {
        return this.value;
    }
}
