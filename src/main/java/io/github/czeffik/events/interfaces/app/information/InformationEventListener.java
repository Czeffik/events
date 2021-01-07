package io.github.czeffik.events.interfaces.app.information;

import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class InformationEventListener {

    private final InformationService informationService;

    @EventListener
    public void enrichPrice(InformationUpdateReceivedEvent event) {
        informationService.enrichPrice(event);
    }
}
