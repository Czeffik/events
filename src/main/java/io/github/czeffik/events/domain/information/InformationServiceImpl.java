package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
import io.github.czeffik.events.domain.information.events.PriceEnrichedEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Clock;

@RequiredArgsConstructor
class InformationServiceImpl implements InformationService {

    private final @NonNull Clock clock;
    private final @NonNull InformationPriceEnricher priceEnricher;
    private final @NonNull InformationEventPublisher publisher;

    @Override
    public void enrichPrice(StartProcessingEvent event) {
        final InformationPriceEnricher.InformationPrice informationPrice =
            priceEnricher.enrich(event.getInformation().getId());
        final PriceEnrichedEvent priceEnrichedEvent = informationPrice.toPriceEnrichedEvent(
            event,
            clock.instant()
        );
        publisher.publish(priceEnrichedEvent);
    }
}
