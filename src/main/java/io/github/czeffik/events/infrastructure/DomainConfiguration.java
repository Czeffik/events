package io.github.czeffik.events.infrastructure;

import io.github.czeffik.events.domain.information.InformationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    InformationService informationService() {
        return event -> null;
    }
}
