package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {


    @Test
    void whenOfferMoreThanPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        NumberCreator numberCreator = new NumberCreator();
        Thread producerThread = new Thread(() -> {
            while (true) {
                queue.offer(numberCreator.createNumber());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumerThread = new Thread(() -> {
            while (true) {
                queue.poll();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }

    @Test
    void whenPollMoreThanOffer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        NumberCreator numberCreator = new NumberCreator();
        Thread producerThread = new Thread(() -> {
            while (true) {
                queue.offer(numberCreator.createNumber());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumerThread = new Thread(() -> {
            while (true) {
                queue.poll();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }

}