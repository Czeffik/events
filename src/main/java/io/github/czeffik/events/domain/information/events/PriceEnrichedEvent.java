package io.github.czeffik.events.domain.information.events;

import io.github.czeffik.events.domain.information.InformationService;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.Instant;

@ToString
@Getter
@Builder
public class PriceEnrichedEvent implements InformationEvent {
    @Builder.Default
    private final @NonNull String id = RandomUtil.getRandomId();
    private final @NonNull Instant timestamp;
    private final @NonNull InformationService.InformationWithPrice information;
}
