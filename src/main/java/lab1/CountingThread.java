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
//    public synchronized void run() {
//        int i = 0;
//        while (i < repeats) {
//            while (counter.getValue() != 0) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (action == '+') {
//                counter.increment();
//                i++;
//                while (counter.getValue() != 0) {
//                    try {
//                        wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (action == '-') {
//                    counter.decrement();
//                    i++;
//                }
//                notifyAll();
//
//                System.out.println("Value: " + counter.getValue());
//            }
//        }
//    }
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
