package io.github.czeffik.events.infrastructure.rest.emitter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

class EmitterStoreImpl<T> implements EmitterStore<T> {
    private final Set<Consumer<T>> emitters = new CopyOnWriteArraySet<>();

    @Override
    public void add(Consumer<T> emitter) {
        emitters.add(emitter);
    }

    @Override
    public Set<Consumer<T>> getAll() {
        return new HashSet<>(emitters);
    }

    @Override
    public void remove(Consumer<T> emitter) {
        this.emitters.remove(emitter);
    }
}
