package lab1;

public class Main {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++) {
                PrintSymbol thr1 = new PrintSymbol("-");
                PrintSymbol thr2 = new PrintSymbol("|");
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
