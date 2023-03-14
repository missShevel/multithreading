package lab1;

public class ThreadQueue {
    private String currentTurn;

    public ThreadQueue() {
        currentTurn = "-";
    }

    public synchronized void waitForTurn(String expectedTurn) throws InterruptedException {
        while (!currentTurn.equals(expectedTurn)) {
            wait();
        }
    }

    public synchronized void nextTurn() {
        if (currentTurn.equals("-")) {
            currentTurn = "|";
        } else {
            currentTurn = "-";
        }
        notifyAll();
    }
}

