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
        Sums[] sums = RowColSum.sum(matrix);
        Sums[] expected = {
                new Sums(10, 6),
                new Sums(14, 11),
                new Sums(10, 7),
                new Sums(4, 14)
        };
        assertThat(sums).isEqualTo(expected);
        }

    @Test
    void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3, 4},
                {4, 3, 3, 4},
                {0, 5, 0, 5},
                {1, 1, 1, 1}

        };
        Sums[] sums = RowColSum.asyncSum(matrix);
        Sums[] expected = {
                new Sums(10, 6),
                new Sums(14, 11),
                new Sums(10, 7),
                new Sums(4, 14)
        };
        assertThat(sums).isEqualTo(expected);
    }
}