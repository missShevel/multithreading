package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static int[][] multiplyMatrixStripes(int[][] matrix1, int[][] matrix2) throws InterruptedException {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;
        if (columns1 != rows2) {
            throw new IllegalArgumentException("Умова множення матриць не виконується (к-ть рядків першої має відповідати к-ті колонок другої)");
        }
        int[][] result = new int[rows1][columns2];
        int[][] transposedMatrix2 = MatrixesManager.transpose(matrix2);
        ExecutorService executor = Executors.newFixedThreadPool(rows1);
        List<Callable<Object>> tasks = new ArrayList<>(rows1);

        for (int i = 0; i < rows1; i++) { // кількість процесів
            for (int j = 0; j < columns2; j++) { // кількість ітерацій процесу
                int col = (j - 1 + columns2) % columns2; // циклічна пересилка стовбців другої матриці
                tasks.add(Executors.callable(new StripeThread(result, matrix1[i], transposedMatrix2[col], i, col)));
            }
        }

        try {
            executor.invokeAll(tasks);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

        return result;
    }


    public static int[][] multiplyMatrixFox(int[][] matrix1, int[][] matrix2, int blockSize) throws InterruptedException {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;
        if (columns1 != rows2) {
            throw new IllegalArgumentException("Умова множення матриць не виконується (к-ть рядків першої має відповідати к-ті колонок другої)");
        }
        if (rows1 % blockSize != 0 || columns1 % blockSize != 0 || rows2 % blockSize != 0 || columns2 % blockSize != 0) {
            throw new IllegalArgumentException("Матриця не може бути рівномірно поділена на блоки із розміром" + blockSize);
        }
        int numBlocks = rows1 / blockSize; // кількість блоків
        int[][] result = new int[rows1][rows1]; // ініціалізація результуючої матриці
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < rows1; j++) {
                result[i][j] = 0;
            }
        }

        int[][][][] blocks1 = MatrixesManager.splitMatrixIntoBlocks(matrix1, blockSize); // Блоки матриці A
        int[][][][] blocks2 = MatrixesManager.splitMatrixIntoBlocks(matrix2, blockSize); // Блоки матриці B
        int[][][][] cBlocks = MatrixesManager.splitMatrixIntoBlocks(result, blockSize); // Блоки матриці C
        FoxThread[][] threads = new FoxThread[numBlocks][numBlocks];

        for (int k = 0; k < numBlocks; k++) {
            for (int i = 0; i < numBlocks; i++) {
                for (int j = 0; j < numBlocks; j++) {
                    threads[i][j] = new FoxThread(cBlocks, blocks1[i][(i + k) % numBlocks], blocks2[(i + k) % numBlocks][j], i, j, blockSize);
                    threads[i][j].start();
                }
            }
        }

        for (int i = 0; i < numBlocks; i++) {
            for (int j = 0; j < numBlocks; j++) {
                try {
                    threads[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        MatrixesManager.convertToMatrix(cBlocks, result);
        return result;
    }

    public static void main(String[] args) {
        int SIZE = 1000;
        int[][] matrix1 = MatrixesManager.generateMatrix(SIZE, SIZE);
//        int[][] matrix1 = {
//                {2, 3, 1, 3, 3, 3},
//                {1, 1, 2, 1, 3, 3},
//                {2, 3, 3, 1, 2, 3},
//                {2, 1, 1, 2, 1, 3},
//                {2, 2, 3, 1, 1, 2},
//                {3, 1, 2, 1, 1, 2}
//        };
//        System.out.println("FIRST MATRIX");
//        MatrixesManager.printMatrix(matrix1);

        int[][] matrix2 = MatrixesManager.generateMatrix(SIZE, SIZE);
//        int[][] matrix2 = {
//                {3, 2, 3, 3, 3, 3},
//                {1, 3, 1, 3, 2, 2},
//                {2, 3, 3, 3, 1, 1},
//                {2, 1, 3, 3, 2, 3},
//                {1, 2, 1, 1, 3, 1},
//                {3, 2, 3, 1, 1, 1}
//        };
//        System.out.println("SECOND MATRIX");
//        MatrixesManager.printMatrix(matrix2);

        try {
//            long startSimple = System.currentTimeMillis();
//            Result resSimple = new Result(MatrixesManager.multiplyBlocks(matrix1, matrix2));
////            System.out.println("Result Fox");
////            resFox.printResult();
//            long endSimple = System.currentTimeMillis();

//            long startStripes = System.currentTimeMillis();
//            Result resStripes = new Result(multiplyMatrixStripes(matrix1, matrix2));
////            System.out.println("Result Stripes");
////            resStripes.printResult();
//            long endStripes = System.currentTimeMillis();

            long startFox = System.currentTimeMillis();
            Result resFox = new Result(multiplyMatrixFox(matrix1, matrix2, 250));
//            System.out.println("Result Fox");
//            resFox.printResult();
            long endFox = System.currentTimeMillis();

//            long elapsedSimple = endSimple - startSimple;
//            long elapsedStripes = endStripes - startStripes;
            long elapsedFox = endFox - startFox;

//            System.out.println("Simple algorithm took " + elapsedSimple + " ms");
//            System.out.println("Stripes algorithm took " + elapsedStripes + " ms");
            System.out.println("Fox algorithm took " + elapsedFox + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
