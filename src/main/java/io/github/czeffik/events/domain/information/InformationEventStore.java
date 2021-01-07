package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.InformationEvent;

import java.util.List;

public interface InformationEventStore {

    void store(InformationEvent event);

    default void store(List<InformationEvent> events) {
        events.forEach(this::store);
    }
}
