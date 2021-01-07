package io.github.czeffik.events.domain.information.events;

import java.time.Instant;

public interface InformationEvent {

    Instant getTimestamp();

    String getId();

    String getEventId();
}
