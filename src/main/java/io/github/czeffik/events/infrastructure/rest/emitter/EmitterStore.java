package io.github.czeffik.events.infrastructure.rest.emitter;

import java.util.Set;
import java.util.function.Consumer;

public interface EmitterStore<T> {

    void add(Consumer<T> emitter);

    Set<Consumer<T>> getAll();

    void remove(Consumer<T> emitter);
}
