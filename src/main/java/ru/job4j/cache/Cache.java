package ru.job4j.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) throws OptimisticException {
        return memory.putIfAbsent(model.id(), model) == null;
    }

    public boolean update(Base model) throws IllegalStateException {
        return memory.computeIfPresent(model.id(), (key, value) -> {
                Base stored = memory.get(model.id());
                if (stored.version() != model.version()) {
                        throw new IllegalStateException("Versions are not equal");
                }
                int newVersion = model.version() + 1;
                return new Base(key, model.name(), newVersion);
        }) != null;
    }

    public void delete(int id) {
        memory.remove(id);
    }

    public Optional<Base> findById(int id) {
        return Optional.ofNullable(memory.get(id));
    }
}