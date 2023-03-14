package lab1;

public class PrintSymbol extends Thread{
    private String symbol;
    private ThreadQueue queue;
    
    public PrintSymbol(String symbol, ThreadQueue queue){
        this.symbol = symbol;
        this.queue = queue;
    }
    
    public void run(){
        for (int i = 0; i < 50; i++) {
            try {
                queue.waitForTurn(symbol);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print(symbol);
            queue.nextTurn();
        }
    }
}
