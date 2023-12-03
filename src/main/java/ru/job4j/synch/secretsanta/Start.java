package ru.job4j.synch.secretsanta;

import ru.job4j.synch.secretsanta.menuitems.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Start {
    private void start(Scanner scanner, List<Item> menuItems, SecretSanta secretSanta) {
        boolean running = true;
        while (running) {
            showMenu(menuItems);
            System.out.println("Enter the number to choose an action.");
            int select = Integer.parseInt(scanner.nextLine());
            if (select < 0 || select >= menuItems.size()) {
                System.out.println("Wrong number, try again.");
                continue;
            }
            running = menuItems.get(select).run(scanner, secretSanta);
        }

    }

    private void showMenu(List<Item> menuItems) {
        System.out.println("Menu:");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i).getName());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> menuItems = Arrays.asList(
                new RegisterNewSanta(),
                new SeeMyTargetPerson(),
                new Exit()
        );
        System.out.println("Please, enter the number of participants:");
        int number = Integer.parseInt(scanner.nextLine());
        SecretSanta secretSanta = new SecretSanta(number);
        new Start().start(scanner, menuItems, secretSanta);
    }
}

