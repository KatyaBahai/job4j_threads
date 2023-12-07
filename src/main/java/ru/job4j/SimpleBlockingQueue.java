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

    public synchronized void offer(T value) {
        while (queue.size() >= maxNumber) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            queue.add(value);
        System.out.printf("Object %s has been produced\n", value);
            this.notify();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T value = queue.poll();
        System.out.printf("Object %s has been consumed\n", value);
        this.notify();
        return value;
}
}
