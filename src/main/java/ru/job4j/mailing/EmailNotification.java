package ru.job4j.mailing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.name(), user.email());
        String body = String.format("Add a new event to %s", user.name());
        pool.submit(() -> send(subject, body, user.email()));
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {
            }
}
