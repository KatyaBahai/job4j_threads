package ru.job4j.pools;

import java.util.concurrent.RecursiveTask;

public class IndexFinderTask<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T objectInQuestion;


    public IndexFinderTask(T[] array, int from, int to, T objectInQuestion) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.objectInQuestion = objectInQuestion;
    }


    @Override
    protected Integer compute() {
        if (to - from < 10) {
            for (int i = from; i < to; i++) {
                if (array[i].equals(objectInQuestion)) {
                    return i;
                }
            }
            return -1;
        }
        int middle = (from + to) / 2;
        IndexFinderTask<T> leftFinder = new IndexFinderTask<>(array, from, middle, objectInQuestion);
        IndexFinderTask<T> rightFinder = new IndexFinderTask<>(array, middle + 1, to, objectInQuestion);
        leftFinder.fork();
        rightFinder.fork();
        int firstResult = leftFinder.join();
        int secondResult = rightFinder.join();
        return Math.max(firstResult, secondResult);
    }
}


