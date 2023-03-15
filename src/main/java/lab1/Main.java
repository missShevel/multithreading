package lab1;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        CountingThread thr_1 = new CountingThread(counter, '+');
        CountingThread thr_2 = new CountingThread(counter, '-');

        thr_1.start();
        thr_2.start();

        thr_1.join();
        thr_2.join();

        System.out.println(counter.getValue());
    }
}
