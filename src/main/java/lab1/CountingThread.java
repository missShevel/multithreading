package lab1;

public class CountingThread extends Thread {
    private Counter counter;
    private char action;
    private int repeats = 100000;

    public CountingThread(Counter counter, char action) {
        this.counter = counter;
        this.action = action;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; i++){
            if (action == '+') {
                synchronized (counter) {
                    counter.increment();
                }
            } else {
                synchronized (counter) {
                    counter.decrement();
                }
            }
            System.out.println("Value: " + counter.getValue());
        }
    }
}
