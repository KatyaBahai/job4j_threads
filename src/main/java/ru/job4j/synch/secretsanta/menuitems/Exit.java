package ru.job4j.synch.secretsanta.menuitems;

import ru.job4j.synch.secretsanta.SecretSanta;

import java.util.Scanner;

public class Exit implements Item {
    public static final String NAME = "Exit the game";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean run(Scanner scanner, SecretSanta santaList) {
        System.out.println("Exiting the game.");
        return false;
    }
}
