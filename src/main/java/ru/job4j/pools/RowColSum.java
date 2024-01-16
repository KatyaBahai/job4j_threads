package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = calculateSums(matrix, i);
        }
        return sums;
    }

    private static Sums calculateSums(int[][] matrix, int i) {
        int rowSum = 0;
        int colSum = 0;
        Sums sum = new Sums(rowSum, colSum);
        for (int j = 0; j < matrix.length; j++) {
           rowSum  += matrix[i][j];
           colSum += matrix[j][i];
        }
        sum.setRowSum(rowSum);
        sum.setColSum(colSum);
        return sum;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Map<Integer, CompletableFuture<Sums>> futureSumsMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int finalI = i;
            futureSumsMap.put(i, CompletableFuture.supplyAsync(
                    () -> calculateSums(matrix, finalI)
            ));
        }
        Sums[] sums = new Sums[matrix.length];
        for (Integer key : futureSumsMap.keySet()) {
            sums[key] = futureSumsMap.get(key).get();
        }
        return sums;
    }
}