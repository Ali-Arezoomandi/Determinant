package Determinant.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class _main_ {

    // Generate Matrix 

    public static int[][] generateRandomMatrix(int n, int min, int max) {
        int[][] matrix = new int[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(max - min);
            }
        }
        return matrix;
    }
    
    // Recursive 

    public static double calcuteDetR(int matrix[][], int j){
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Invalid input!");
        } else if (matrix.length == 1) {
            return matrix[0][0];
        } else if (j == matrix[0].length) {
            return 0;
        } else{
            return (matrix[0][j] * Math.pow(-1, j) * calcuteDetR(calcuteMinor(matrix, j), 0)) + calcuteDetR(matrix, j + 1);
        }
    }

    public static int[][] calcuteMinor(int matrix[][], int j){
        int[][] newMatrix = new int[matrix.length - 1][matrix[0].length - 1];
        for (int i = 1; i < matrix.length; i++) { 
            int newCol = 0;
            for (int k = 0; k < matrix[0].length; k++) {
                if (k != j) {
                    newMatrix[i - 1][newCol] = matrix[i][k];
                    newCol++;
                }
            }
        }
        return newMatrix;
    }  

    // Gaussian

    public static double calcuteDetG(double[][] matrix){
        if(matrix.length != matrix[0].length){
            throw new IllegalArgumentException("Invalid input!");
        }
        
        int count = convertUT(matrix);

        double sum = 1;
        if (matrix.length == 1) {
            return matrix[0][0];
        } else {
        for (int i = 0; i < matrix.length; i++) {
            sum *= matrix[i][i];
            }
        }
        if (count % 2 != 0) {  
            return sum * (-1);
        } else{
            return sum;
        }
    }

    public static int convertUT(double matrix[][]){
        int count = 0;
        for (int i = 0; i < matrix.length - 1; i++) {
           if (matrix[i][i] == 0) {
                for (int x = i + 1; x < matrix.length; x++) {
                    if (matrix[x][i] != 0) {
                        double[] n = matrix[x];
                        matrix[x] = matrix[i];
                        matrix[i] = n;
                        count++;
                        break;
                    }
                }
           }
            double pivot = matrix[i][i];
            for (int j = i + 1; j < matrix.length; j++) { // row
                double zarib = matrix[j][i] / pivot;
                for (int k = i; k < matrix.length; k++) {  // colum
                    matrix[j][k] = (double)(matrix[j][k] - (zarib * matrix[i][k]));
                }
            }
            }
            return count; 
        }


    // LU

    public static double calcuteDetLU(double[][] matrix){
        int n = matrix.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        for (int i = 0; i < n; i++) {
            
            // Upper Triangular

            for (int k = i; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++){
                    sum += (L[i][j] * U[j][k]);
                }
                U[i][k] = matrix[i][k] - sum;
            }

            // Lower Triangular

            for (int k = i; k < n; k++) {
                if (i == k){
                    L[i][i] = 1; 
                } else {
                    double sum = 0;
                    for (int j = 0; j < i; j++){
                        sum += (L[k][j] * U[j][i]);
                    }
                    L[k][i] = (matrix[k][i] - sum) / U[i][i];
                }
            }
        }

        // Determinant 

        double detLU = 1.0;
        for (int i = 0; i < n; i++) {
            detLU *= U[i][i] * L[i][i];
        }

        return detLU;
    }

    // Wirte on File

    public static void writeOnFile(int[][] matrix, double det_R, double delta_R, double det_G, double delta_G,  double det_LU, double delta_LU,String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            // Write Recursive results

            bw.write("With Recursive:");
            bw.newLine();
            bw.write("\tMatrix:");
            bw.newLine();
            for (int[] row : matrix) {
                bw.write("\t"+Arrays.toString(row));
                bw.newLine();
            }
            bw.write("\tDeterminant: " + det_R);
            bw.newLine();
            bw.write("\tDelta time (ms): " + delta_R);
            bw.newLine();
            bw.newLine();
            
            // Write Gaussian results

            bw.write("With Gaussian:");
            bw.newLine();
            bw.write("\tMatrix:");
            bw.newLine();
            for (int[] row : matrix) {
                bw.write("\t"+Arrays.toString(row));
                bw.newLine();
            }
            bw.write("\tDeterminant: " + det_G);
            bw.newLine();
            bw.write("\tDelta time (ms): " + delta_G);
            bw.newLine();
            bw.newLine(); 

            // Write LU results

            bw.write("With LU:");
            bw.newLine();
            bw.write("\tMatrix:");
            bw.newLine();
            for (int[] row : matrix) {
                bw.write("\t"+Arrays.toString(row));
                bw.newLine();
            }
            bw.write("\tDeterminant: " + det_LU);
            bw.newLine();
            bw.write("\tDelta time (ms): " + delta_LU);
            bw.newLine();
            bw.newLine();
            
            String result = "Gaussian's time = " + delta_G + "  is faster than  " + "Recursive's time = " + delta_R;
            bw.write("Compare: \n" + "\t" + result);
            bw.newLine(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("n: ");
        int n = sc.nextInt();
        System.out.println();
        sc.close();

        int[][] matrix = generateRandomMatrix(n, 0, 10);
        int size = matrix.length;
        double[][] matrix_for_G = new double[size][size];
        double[][] matrix_for_LU = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix_for_G[i][j] = matrix[i][j]; 
                matrix_for_LU[i][j] = matrix[i][j]; 
            }
        }

        // Recursive 

        long startTime = System.nanoTime();
        double det_R = calcuteDetR(matrix, 0);
        System.out.println("Recursive_Det is: " + det_R);
        long endTime = System.nanoTime();
        double delta_R = (endTime - startTime) / 1e6; 
        System.out.println("Recursive_Delta_time (ms): " + delta_R);
        System.out.println();

        // Gaussian

        startTime = System.nanoTime();
        double det_G = calcuteDetG(matrix_for_G);
        System.out.println("Gaussian_Det is: " + det_G);
        endTime = System.nanoTime();
        double delta_G = (endTime - startTime) / 1e6; 
        System.out.println("Gaussian_Delta_time (ms): " + delta_G);
        
        // LU

        startTime = System.nanoTime();
        double det_LU = calcuteDetLU(matrix_for_LU);
        System.out.println("\nLU_Det is: " + det_LU);
        endTime = System.nanoTime();
        double delta_LU = (endTime - startTime) / 1e6; 
        System.out.println("LU_Delta_time (ms): " + delta_LU);
        System.out.println();
        
        // Write On File

       writeOnFile(matrix, det_R, delta_R, det_G, delta_G, det_LU, delta_LU, "output.txt");
    }
}