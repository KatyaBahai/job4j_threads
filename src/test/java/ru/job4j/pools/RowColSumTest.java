package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RowColSumTest {

    @Test
    void sum() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {4, 3, 3, 4},
                {0, 5, 0, 5},
                {1, 1, 1, 1}

        };
        RowColSum.Sums[] sums = RowColSum.sum(matrix);
        int[][] expected = {
                {10, 6},
                {14, 11},
                {10, 7},
                {4, 14}
        };
        int[][] result = new int[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
                result[i][0] = sums[i].getColSum();
                result[i][1] = sums[i].getRowSum();
            }
        assertThat(result).isEqualTo(expected);
        }

    @Test
    void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3, 4},
                {4, 3, 3, 4},
                {0, 5, 0, 5},
                {1, 1, 1, 1}

        };
        RowColSum.Sums[] sums = RowColSum.asyncSum(matrix);
        int[][] expected = {
                {10, 6},
                {14, 11},
                {10, 7},
                {4, 14}
        };
        int[][] result = new int[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            result[i][0] = sums[i].getColSum();
            result[i][1] = sums[i].getRowSum();
        }
        assertThat(result).isEqualTo(expected);
    }
}