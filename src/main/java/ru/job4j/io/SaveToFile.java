package ru.job4j.io;

import java.io.*;

public class SaveToFile {
    private final File file;

    public SaveToFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (int i = 0; i < content.length(); i++) {
                writer.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
