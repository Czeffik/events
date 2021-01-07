package io.github.czeffik.events.interfaces.app.information;

import io.github.czeffik.events.domain.information.InformationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationEventListenerConfiguration {

    @Bean
    public InformationEventListener informationEventListener(
        InformationService informationService
    ) {
        return new InformationEventListener(informationService);
    }
}