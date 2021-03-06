package io.github.czeffik.events.domain.information.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@ToString
@Getter
@Builder
public class InformationWithPriceEvent implements InformationEvent {
    @Builder.Default
    private final String eventId = RandomUtil.getRandomId();
    private final @NonNull Instant timestamp;
    private final @NonNull String id;
    private final @NonNull String name;
    private final @NonNull String description;
    private final @NonNull BigDecimal price;
}
