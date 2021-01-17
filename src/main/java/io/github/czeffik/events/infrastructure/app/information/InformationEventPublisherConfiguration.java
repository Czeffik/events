package io.github.czeffik.events.infrastructure.app.information;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationEventPublisherConfiguration {

    @Bean
    public InformationEventPublisher informationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new InformationEventPublisherImpl(applicationEventPublisher);
    }
}
