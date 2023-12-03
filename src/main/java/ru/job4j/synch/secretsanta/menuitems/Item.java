package ru.job4j.synch.secretsanta.menuitems;

import ru.job4j.synch.secretsanta.SecretSanta;

import java.util.Scanner;

public interface Item {
     String getName();

     boolean run(Scanner scanner, SecretSanta santaList);
}
