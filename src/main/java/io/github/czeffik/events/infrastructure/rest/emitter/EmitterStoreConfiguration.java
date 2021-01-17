package io.github.czeffik.events.infrastructure.rest.emitter;

import io.github.czeffik.events.domain.information.events.InformationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmitterStoreConfiguration {

    @Bean
    EmitterStore<InformationEvent> informationEventEmitterStore() {
        return new EmitterStoreImpl<>();
    }
}
