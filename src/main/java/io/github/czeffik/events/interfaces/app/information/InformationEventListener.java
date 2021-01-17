package io.github.czeffik.events.interfaces.app.information;

import io.github.czeffik.events.domain.information.InformationEventStore;
import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
import io.github.czeffik.events.domain.information.events.PriceEnrichedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class InformationEventListener {

    private final InformationService informationService;
    private final InformationEventStore eventStore;

    @EventListener
    public void enrichPrice(StartProcessingEvent event) {
        informationService.enrichPrice(event);
    }

    @EventListener(classes = {
        StartProcessingEvent.class,
        PriceEnrichedEvent.class
    })
    public void storeEvent(InformationEvent event) {
        eventStore.store(event);
    }
}
