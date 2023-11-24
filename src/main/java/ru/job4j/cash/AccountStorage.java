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
        Account newAccount = accounts.putIfAbsent(account.id(), account);
        return newAccount == null;
    }

    public synchronized boolean update(Account account) {
        Account updAccount = accounts.computeIfPresent(account.id(), (id, value) -> value = account);
        return updAccount != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        int fromAmount = accounts.get(fromId).amount();
        if (fromAmount >= amount) {
            update(new Account(fromId, fromAmount - amount));
            update(new Account(toId, accounts.get(toId).amount() + amount));
            rsl = true;
        }
        return rsl;
    }
}
