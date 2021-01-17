package io.github.czeffik.events.infrastructure.rest;

import io.github.czeffik.events.domain.information.InformationPriceEnricher;
import io.github.czeffik.events.infrastructure.rest.emitter.EmitterStoreConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    EmitterStoreConfiguration.class
})
@Configuration
public class RestInfrastructureConfiguration {

    @Bean
    InformationPriceEnricher informationPriceEnricher() {
        return new FakeInformationPriceEnricher();
    }
}
