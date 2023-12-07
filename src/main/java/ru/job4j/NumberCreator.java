package ru.job4j;

public class NumberCreator {
    private int count = 0;

    public int createNumber() {
        return count++;
    }
}
