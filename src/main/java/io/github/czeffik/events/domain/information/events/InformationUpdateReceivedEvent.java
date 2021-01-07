package io.github.czeffik.events.domain.information.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@ToString
@Getter
@RequiredArgsConstructor
public class InformationUpdateReceivedEvent implements InformationEvent {
    private final Instant timestamp;
    private final String id;
    private final String name;
    private final String description;
}
