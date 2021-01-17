package io.github.czeffik.events.interfaces.app.information;

import io.github.czeffik.events.domain.information.InformationEventStore;
import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.infrastructure.rest.emitter.EmitterStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationEventListenerConfiguration {

    @Bean
    public InformationEventListener informationEventListener(
        InformationService informationService,
        InformationEventStore informationEventStore,
        EmitterStore<InformationEvent> informationEventEmitterStore
    ) {
        return new InformationEventListener(informationService, informationEventStore, informationEventEmitterStore);
    }
}
