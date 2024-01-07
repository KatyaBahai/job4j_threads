package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import static org.assertj.core.api.Assertions.*;

class IndexFinderTaskTest {

    @Test
    void whenObjectPresentThenIndexFound() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        IndexFinderTask<Integer> task = new IndexFinderTask<>(array, 0, array.length, 15);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int result = forkJoinPool.invoke(task);
        System.out.println(result);
        assertThat(result).isPositive();
    }

    @Test
    void whenObjectNotPresentThenIndexNotFound() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        IndexFinderTask<Integer> task = new IndexFinderTask<>(array, 0, array.length, 1500);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(task);
        System.out.println(result);
        assertThat(result).isNegative();
    }

    @Test
    void whenArrayIsNoBiggerThenThreshold() {
        Integer[] array = new Integer[9];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        IndexFinderTask<Integer> task = new IndexFinderTask<>(array, 0, array.length, 15);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(task);
        System.out.println(result);
    }

    @Test
    void whenArrayHasStringsThenIndexFound() {
        String[] array = new String[15];
        String startingString = "a";
        for (int i = 0; i < array.length; i++) {
            array[i] = startingString;
            startingString += "a";
        }
        IndexFinderTask<String> task = new IndexFinderTask<>(array, 0, array.length, "aaaaa");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(task);
        System.out.println(result);
        assertThat(result).isPositive();
    }

}