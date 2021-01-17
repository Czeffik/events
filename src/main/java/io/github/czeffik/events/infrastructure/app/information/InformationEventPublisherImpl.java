package io.github.czeffik.events.infrastructure.app.information;

import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.InformationEventPublisher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class InformationEventPublisherImpl implements InformationEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(InformationEvent event) {
        this.publisher.publishEvent(event);
    }
}
