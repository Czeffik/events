package io.github.czeffik.events.domain.information.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@ToString
@Getter
@RequiredArgsConstructor
public class InformationWithPriceEvent implements InformationEvent {
    private final Instant timestamp;
    private final String id;
    private final String name;
    private final String description;
    private final BigDecimal price;
}
