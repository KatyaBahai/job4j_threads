package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String destFile;

    public Wget(String url, int speed, String destFile) {
        this.url = url;
        this.speed = speed;
        this.destFile = destFile;
    }

    @Override
    public void run() {
        var startAt = System.currentTimeMillis();
        File file = new File(destFile);
        try (var in = new URL(url).openStream();
             var out = new FileOutputStream(file)) {
            System.out.println("Open connection: " + (System.currentTimeMillis() - startAt) + " ms");
            var dataBuffer = new byte[1024];
            int bytesRead;
            int totalBytesRead = 0;
            long downloadStart = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                if (totalBytesRead >= speed) {
                    double time = System.currentTimeMillis() - downloadStart;
                    if (time < 1000) {
                        try {
                            long sleepTime = (long) (1000 - time);
                            Thread.sleep(sleepTime);
                            System.out.println("pause: " + sleepTime);
                            totalBytesRead = 0;
                            downloadStart = System.currentTimeMillis();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        paramValidate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String destFile = args[2];
        Thread wget = new Thread(new Wget(url, speed, destFile));
        wget.start();
        wget.join();
    }

    private static void paramValidate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Use ROOT_FOLDER");
        }
        if (args.length > 3) {
            throw new IllegalArgumentException("Root folder must have 3 parameters. Use ROOT_FOLDER");
        }
        if (args.length < 3) {
            throw new IllegalArgumentException("Parameter is missing. Use ROOT-FOLDER");
        }
        try {
            URL url = new URL(args[0]);
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("The 1 parameter in Root Folder is not a url.");
        }
        if (!isInteger(args[1])) {
            throw new IllegalArgumentException("The 2 parameter should be an integer.");
        }
        try {
            Path destFile = Paths.get(args[2]);
        } catch (InvalidPathException ex) {
            throw new InvalidPathException(args[2], "The 3 parameter in Root Folder is not a path.");
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
