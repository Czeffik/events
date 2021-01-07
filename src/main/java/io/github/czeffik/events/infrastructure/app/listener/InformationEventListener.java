package io.github.czeffik.events.infrastructure.app.listener;

import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class InformationEventListener {

    private final InformationService informationService;

    @EventListener
    public void enrichPrice(InformationUpdateReceivedEvent event) {
        informationService.enrichPrice(event);
    }
}
