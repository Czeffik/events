package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import io.github.czeffik.events.domain.information.events.InformationWithPriceEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Clock;

@RequiredArgsConstructor
class InformationServiceImpl implements InformationService {

    private final @NonNull Clock clock;
    private final @NonNull InformationPriceEnricher priceEnricher;
    private final @NonNull InformationEventPublisher publisher;

    @Override
    public void enrichPrice(InformationUpdateReceivedEvent event) {
        final InformationPriceEnricher.InformationPrice informationPrice = priceEnricher.enrich(event.getId());
        final InformationWithPriceEvent eventWithPrice = informationPrice.toInformationWithPriceEvent(
            event,
            clock.instant()
        );
        publisher.publish(eventWithPrice);
    }
}
