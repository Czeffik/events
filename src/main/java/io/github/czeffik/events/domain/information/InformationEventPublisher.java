package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.InformationEvent;

import java.util.List;

public interface InformationEventPublisher {

    void publish(InformationEvent event);

    default void publish(List<InformationEvent> events) {
        events.forEach(this::publish);
    }
}
