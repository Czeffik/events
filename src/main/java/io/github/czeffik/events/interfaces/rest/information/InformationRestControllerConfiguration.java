package io.github.czeffik.events.interfaces.rest.information;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.infrastructure.rest.emitter.EmitterStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationRestControllerConfiguration {

    @Bean
    InformationController informationController(
        EmitterStore<InformationEvent> informationEventEmitterStore,
        InformationEventPublisher informationEventPublisher
    ) {
        return new InformationController(informationEventEmitterStore, informationEventPublisher);
    }

}
