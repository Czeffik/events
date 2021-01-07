package io.github.czeffik.events.interfaces.app.information;

import io.github.czeffik.events.domain.information.InformationEventStore;
import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import io.github.czeffik.events.domain.information.events.InformationWithPriceEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class InformationEventListener {

    private final InformationService informationService;
    private final InformationEventStore eventStore;

    @EventListener
    public void enrichPrice(InformationUpdateReceivedEvent event) {
        informationService.enrichPrice(event);
    }

    @EventListener(classes = {
        InformationUpdateReceivedEvent.class,
        InformationWithPriceEvent.class
    })
    public void storeEvent(InformationEvent event) {
        eventStore.store(event);
    }
}
