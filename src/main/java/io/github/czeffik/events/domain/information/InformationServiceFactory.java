package io.github.czeffik.events.domain.information;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Clock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InformationServiceFactory {

    public static InformationService create(
        final Clock clock,
        final InformationPriceEnricher informationPriceEnricher,
        final InformationEventPublisher informationEventPublisher
    ) {
        return new InformationServiceImpl(clock, informationPriceEnricher, informationEventPublisher);
    }
}
