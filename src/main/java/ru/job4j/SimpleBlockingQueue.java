package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int maxNumber;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int maxNumber) {
        this.maxNumber = maxNumber;

    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= maxNumber) {
                this.wait();
        }
            queue.add(value);
        System.out.printf("Object %s has been produced\n", value);
            this.notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                this.wait();
        }
        T value = queue.poll();
        System.out.printf("Object %s has been consumed\n", value);
        this.notify();
        return value;
}
}
