package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
     private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContentWithUnicode() {
        return getContent((data) -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        return getContent((data) ->  data < 0x80);
    }

    private String getContent(Predicate<Character> predicate) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = reader.read()) != -1) {
                Character character = (char) data;
                if (predicate.test(character)) {
                    builder.append(character);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}