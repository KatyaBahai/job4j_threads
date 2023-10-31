package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var startAt = System.currentTimeMillis();
        File file = new File("tmp.xml");
        try (var in = new URL(url).openStream();
             var out = new FileOutputStream(file)) {
            System.out.println("Open connection: " + (System.currentTimeMillis() - startAt) + " ms");
            var dataBuffer = new byte[512];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                var downloadAt = System.nanoTime();
                out.write(dataBuffer, 0, bytesRead);
                double writtenAt = (System.nanoTime() - downloadAt) / 1000000.0;
                long currentSpeedBytePerMsecond = (long) (bytesRead / writtenAt);
                System.out.println("Read " + bytesRead + " bytes : " + currentSpeedBytePerMsecond + " mSec.");
                long diff = currentSpeedBytePerMsecond / speed;
                if (diff > 1) {
                    try {
                        Thread.sleep(diff);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    private static void paramValidate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Use ROOT_FOLDER");
        }
            if (args.length > 2) {
                throw new IllegalArgumentException("Root folder must have 2 parameters. Use ROOT_FOLDER");
            }
            if (args.length < 2) {
                throw new IllegalArgumentException("Parameter is missing. Use ROOT-FOLDER");
            }
            try {
                URL url = new URL(args[0]);
            } catch (MalformedURLException ex) {
                throw new InvalidPathException(args[0], "The 1 parameter in Root Folder is not a url.");
            }
            if (!isInteger(args[1])) {
                    throw new IllegalArgumentException("The 2 parameter should be an integer. Use ROOT-FOLDER");
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
