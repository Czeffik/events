package io.github.czeffik.events.infrastructure.app.publisher;

import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.InformationEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@RequiredArgsConstructor
public class EventPublisher implements InformationEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(InformationEvent event) {
        this.publisher.publishEvent(event);
    }
}
