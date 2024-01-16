package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTest {


    public static void main(String[] args) {
        int[] array = getTheArray();
        List<Integer> finalList = new ArrayList<>();
        for (int j : array) {
            int count = 0;
            for (int k : array) {
                if (j == k) {
                    count++;
                }
            }
            if (count == 1) {
                finalList.add(j);
            }
        }
        finalList.forEach((i) -> System.out.printf("%s ", i));

    }

    private static int[] getTheArray() {
        Scanner scanner = new Scanner(System.in);
        int arraySize = Integer.parseInt(scanner.nextLine());
        String[] arrayString = scanner.nextLine().split(" ");
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = Integer.parseInt(arrayString[i]);
        }
        return array;
    }
}
