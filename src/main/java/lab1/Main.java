package lab1;

public class Main {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++) {
                ThreadQueue queue = new ThreadQueue();
                PrintSymbol thr1 = new PrintSymbol("-", queue);
                PrintSymbol thr2 = new PrintSymbol("|", queue);
                thr1.start();
                thr2.start();

                thr1.join();
                thr2.join();

                System.out.println();

            }

        } catch (InterruptedException e) {

        }
    }
}
