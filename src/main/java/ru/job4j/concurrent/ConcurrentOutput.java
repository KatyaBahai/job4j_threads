package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
            Thread another = new Thread(
                    () -> System.out.println(Thread.currentThread().getName())
            );
            another.start();
            System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            Thread second = new Thread(
                    () -> System.out.println(Thread.currentThread().getName())
            );
            second.start();
        }
}
}
