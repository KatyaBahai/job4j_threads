package ru.job4j.cache;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) throws OptimisticException {
        return memory.putIfAbsent(model.id(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        return memory.computeIfPresent(model.id(), compute(model)) != null;
    }

    public void delete(int id) {
        memory.remove(id);
    }

    public Optional<Base> findById(int id) {
        return Stream.of(memory.get(id))
                .filter(Objects::nonNull)
                .findFirst();
    }

    private BiFunction<Integer, Base, Base> compute(Base model) throws OptimisticException {
        Base stored = memory.get(model.id());
        if (stored.version() != model.version()) {
            throw new OptimisticException("Versions are not equal");
        }
        int newVersion = model.version() + 1;
        return (key, value) -> new Base(key, model.name(), newVersion);
    }
}