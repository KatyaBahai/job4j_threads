package ru.job4j.synch.secretsanta.menuitems;

import ru.job4j.synch.secretsanta.Person;
import ru.job4j.synch.secretsanta.SecretSanta;

import java.util.*;

public class RegisterNewSanta implements Item {
    public static final String NAME = "Register new Santas";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean run(Scanner scanner, SecretSanta secretSanta) {
        secretSanta.getSantaList().clear();
        secretSanta.getPairs().clear();
        int number = secretSanta.getParticipantsNumber();
        List<Person> santaList = secretSanta.getSantaList();
        while (number > 0) {
            System.out.println("Please, enter your name");
            String name = scanner.nextLine();
            Person person = new Person(name);
            if (!santaList.contains(person)) {
                santaList.add(person);
                System.out.printf("Santa %s (#%s) has been successfully registered\n",
                        person.name(),
                        secretSanta.getParticipantsNumber() + 1 - number);
                number--;
            }
        }
        distributePeople(santaList, secretSanta);
        System.out.println("All Santas are registered, you can choose your target person now");
        Item seeMyTargetPerson = new SeeMyTargetPerson();
        seeMyTargetPerson.run(scanner, secretSanta);
        return true;
    }

    private void distributePeople(List<Person> santaList, SecretSanta secretSanta) {
       List<Person> tempList = new ArrayList<>(santaList);
        List<Person> targetPeopleList = new ArrayList<>(santaList);
     Collections.shuffle(targetPeopleList);
     Map<Person, Person> pairs = secretSanta.getPairs();
        Random random = new Random();
        while (pairs.size() != secretSanta.getParticipantsNumber()) {
            int count = santaList.size();
            int choice = random.nextInt(count);
            Person santa = santaList.get(choice);
            Person target = targetPeopleList.get(choice);
            if (!santa.equals(target)) {
                pairs.put(santa, target);
                santaList.remove(choice);
                targetPeopleList.remove(choice);
            } else if (count == 1) {
                pairs.clear();
                santaList.addAll(tempList);
                targetPeopleList.addAll(tempList);
            } else {
                Collections.shuffle(targetPeopleList);
            }
        }
    }
}
