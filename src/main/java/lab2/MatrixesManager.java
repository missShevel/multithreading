package lab2;

import java.util.Random;

public class MatrixesManager {

    public static int[][] generateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(3) + 1;
//                matrix[i][j] = 1;
            }
        }
        return matrix;
    }

    public static void convertToMatrix(int[][][][] arr, int[][] result) {
        int subMatrixSize = arr[0][0].length;
        int numSubMatrices = arr.length;
        for (int i = 0; i < numSubMatrices; i++) {
            for (int j = 0; j < numSubMatrices; j++) {
                int[][] subMatrix = arr[i][j];
                int subMatrixStartRow = i * subMatrixSize;
                int subMatrixStartCol = j * subMatrixSize;
                for (int k = 0; k < subMatrixSize; k++) {
                    for (int l = 0; l < subMatrixSize; l++) {
                        result[subMatrixStartRow + k][subMatrixStartCol + l] = subMatrix[k][l];
                    }
                }
            }
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[][][][] splitMatrixIntoBlocks(int[][] matrix, int blockSize) {
        int numBlocks = matrix.length / blockSize;
        int numBlocks2 = matrix[0].length / blockSize;
        int[][][][] blocks = new int[numBlocks][numBlocks][blockSize][blockSize];

        for (int i = 0; i < numBlocks; i++) {
            for (int j = 0; j < numBlocks2; j++) {
                for (int x = 0; x < blockSize; x++) {
                    System.arraycopy(matrix[i * blockSize + x], j * blockSize, blocks[i][j][x], 0, blockSize);
                }
            }
        }
        return blocks;
    }

    public static int[][] multiplyBlocks(int[][] firstBlock, int[][] secondBlock) {
        int rows1 = firstBlock.length;
        int columns1 = firstBlock[0].length; // same as rows in second matrix
        int columns2 = secondBlock[0].length;
        int[][] result = new int[rows1][columns2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < columns1; k++) {
                    result[i][j] += firstBlock[i][k] * secondBlock[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        int numItems = matrix1.length; // number of items in each row/column
        int[][] result = new int[numItems][numItems];

        for (int i = 0; i < numItems; i++) {
            for (int j = 0; j < numItems; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
