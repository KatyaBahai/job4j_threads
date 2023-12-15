package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int number;
        do {
            number = count.get();
        } while (!count.compareAndSet(number, ++number));


    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread firstThread = new Thread(() ->
                IntStream.range(1, 101).forEach((i) -> casCount.increment())
        );
        Thread secondThread = new Thread(() ->
                IntStream.range(1, 101).forEach((i) -> casCount.increment())
        );
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        System.out.println(casCount.get());
    }


}