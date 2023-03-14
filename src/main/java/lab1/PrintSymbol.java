package lab1;

public class PrintSymbol extends Thread{
    private String symbol;
    
    public PrintSymbol(String symbol){
        this.symbol = symbol;
    }
    
    public void run(){
        for (int i = 0; i < 50; i++) {
            System.out.print(symbol);
        }
    }
}
