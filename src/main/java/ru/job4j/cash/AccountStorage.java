package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.computeIfPresent(account.id(), (id, value) -> value = account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (getById(fromId).isPresent() && getById(toId).isPresent() && accounts.get(fromId).amount() >= amount) {
            update(new Account(fromId, accounts.get(fromId).amount() - amount));
            update(new Account(toId, accounts.get(toId).amount() + amount));
            rsl = true;
        }
        return rsl;
    }
}
