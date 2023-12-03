package ru.job4j.synch.secretsanta.menuitems;

import org.w3c.dom.ls.LSOutput;
import ru.job4j.synch.secretsanta.Person;
import ru.job4j.synch.secretsanta.SecretSanta;

import java.util.Map;
import java.util.Scanner;

public class SeeMyTargetPerson implements Item {
    public static final String NAME = "See my target person";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean run(Scanner scanner, SecretSanta secretSanta) {
        Map<Person, Person> pairs = secretSanta.getPairs();
        boolean isOn = true;
        while (isOn) {
            System.out.println("To see your target person, please, enter your name "
                    + "or enter 'exit' to see the main menu.");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("exit")) {
                Person santa = new Person(answer);
                if (pairs.containsKey(santa)) {
                   System.out.println(pairs.get(santa));
                } else {
                    System.out.println("There's no santa under this name in the game.");
                }
            } else {
                isOn = false;
            }
        }
        return true;
    }
}
