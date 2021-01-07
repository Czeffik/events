package io.github.czeffik.events.infrastructure.app;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import io.github.czeffik.events.infrastructure.app.listener.InformationEventListenerConfiguration;
import io.github.czeffik.events.infrastructure.app.publisher.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    InformationEventListenerConfiguration.class
})
@Configuration
public class AppConfiguration {

    @Bean
    public InformationEventPublisher informationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new EventPublisher(applicationEventPublisher);
    }
}
