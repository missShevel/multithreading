package lab2;

public class StripeThread extends Thread {
    int[][] result;
    int[] row;
    int[] col;
    int rowIndex;
    int colIndex;

    public StripeThread(int[][] result, int[] row, int[] col, int rowIndex, int colIndex) {
        this.result = result;
        this.row = row;
        this.col = col;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    @Override
    public void run() {
        int matrixCols = row.length;
        for (int k = 0; k < matrixCols; k++) {
            result[rowIndex][colIndex] += row[k] * col[k];
        }
    }
}
