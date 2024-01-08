package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
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

    public static <T> int initializeSearchTask(T[] array, T objectInQuestion) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexFinderTask<>(array, 0, array.length, objectInQuestion));
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return getIndex();
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

    private Integer getIndex() {
        for (int i = from; i < to; i++) {
            if (array[i].equals(objectInQuestion)) {
                return i;
            }
        }
        return -1;
    }
}


