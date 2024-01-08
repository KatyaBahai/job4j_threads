package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class IndexFinderTaskTest {

    @Test
    void whenObjectPresentThenIndexFound() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        int result = IndexFinderTask.initializeSearchTask(array, 15);
        System.out.println(result);
        assertThat(result).isPositive();
    }

    @Test
    void whenObjectNotPresentThenIndexNotFound() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        int result = IndexFinderTask.initializeSearchTask(array, 1500);
        System.out.println(result);
        assertThat(result).isNegative();
    }

    @Test
    void whenArrayIsNoBiggerThenThreshold() {
        Integer[] array = new Integer[9];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        int result = IndexFinderTask.initializeSearchTask(array, 7);
        System.out.println(result);
        assertThat(result).isPositive();
    }

    @Test
    void whenArrayHasStringsThenIndexFound() {
        String[] array = new String[15];
        String startingString = "a";
        for (int i = 0; i < array.length; i++) {
            array[i] = startingString;
            startingString += "a";
        }
        int result = IndexFinderTask.initializeSearchTask(array, "aaaaa");
        System.out.println(result);
        assertThat(result).isPositive();
    }

}