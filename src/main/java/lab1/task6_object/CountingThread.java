package lab1.task6_object;

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
        for (int i = 0; i < repeats; i++) {
            if (action == '+') {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    counter.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Value: " + counter.getValue());
        }

    }
}
