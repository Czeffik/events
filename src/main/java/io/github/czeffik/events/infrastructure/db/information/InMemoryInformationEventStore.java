package io.github.czeffik.events.infrastructure.db.information;

import io.github.czeffik.events.domain.information.InformationEventStore;
import io.github.czeffik.events.domain.information.events.InformationEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryInformationEventStore implements InformationEventStore {

    private final Map<String, InformationEvent> store = new ConcurrentHashMap<>();

    @Override
    public void store(InformationEvent event) {
        this.store.put(event.getId(), event);
    }
}
