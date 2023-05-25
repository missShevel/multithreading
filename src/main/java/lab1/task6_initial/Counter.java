package lab1.task6_initial;

public class Counter {
    private int value;

    public void  increment() {
        value += 1;
    }
    public void decrement() {
        value -= 1;
    }
    int getValue() {
        return this.value;
    }
}