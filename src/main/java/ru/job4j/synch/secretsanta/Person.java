package ru.job4j.synch.secretsanta;

public record Person(String name) {
    @Override
    public String toString() {
        return "Your target is " + name;
    }
}
