package io.github.czeffik.events.infrastructure;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import io.github.czeffik.events.domain.information.InformationPriceEnricher;
import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.InformationServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DomainConfiguration {

    @Bean
    public InformationService informationService(
        final Clock clock,
        final InformationPriceEnricher informationPriceEnricher,
        final InformationEventPublisher informationEventPublisher
    ) {
        return InformationServiceFactory.create(clock, informationPriceEnricher, informationEventPublisher);
    }
}
