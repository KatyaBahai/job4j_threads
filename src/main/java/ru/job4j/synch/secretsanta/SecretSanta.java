package ru.job4j.synch.secretsanta;

import ru.job4j.synch.secretsanta.menuitems.Item;

import java.util.*;

public class SecretSanta {
    private final int participantsNumber;
    private final List<Person> santaList = new ArrayList<>();
    private final Map<Person, Person> pairs = new HashMap<>();

    public SecretSanta(int participantsNumber) {
        this.participantsNumber = participantsNumber;
    }



    public List<Person> getSantaList() {
        return santaList;
    }

    public Map<Person, Person> getPairs() {
        return pairs;
    }

    public int getParticipantsNumber() {
        return participantsNumber;
    }
}
