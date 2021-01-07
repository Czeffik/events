package io.github.czeffik.events.infrastructure.rest;

import io.github.czeffik.events.domain.information.InformationPriceEnricher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestInfrastructureConfiguration {

    @Bean
    InformationPriceEnricher informationPriceEnricher() {
        return id -> null;
    }
}
