package lab2;

public class Result {
    private int[][] result;

    public Result(int[][] result) {
        this.result = result;
    }

    public void printResult() {
        for (int[] row : result) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
