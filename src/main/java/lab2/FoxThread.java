package lab2;

public class FoxThread extends Thread {
    int[][][][] result;
    int[][] matrix1;
    int[][] matrix2;
    int row;
    int col;
    int blockSize;

    public FoxThread(int[][][][] result, int[][] m1, int[][] m2, int row, int col, int blockSize) {
        this.result = result;
        matrix1 = m1;
        matrix2 = m2;
        this.row = row;
        this.col = col;
        this.blockSize = blockSize;
    }

    @Override
    public void run() {
        int[][] subResult = MatrixesManager.multiplyBlocks(matrix1, matrix2);
        result[row][col] = MatrixesManager.addMatrices(result[row][col], subResult);

    }
}
