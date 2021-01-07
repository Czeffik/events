package io.github.czeffik.events.infrastructure.db.information;

import io.github.czeffik.events.domain.information.InformationEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationEventStoreConfiguration {

    @Bean
    public InformationEventStore informationEventStore() {
        return event -> {
        };
    }
}
