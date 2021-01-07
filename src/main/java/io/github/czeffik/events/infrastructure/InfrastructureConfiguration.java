package io.github.czeffik.events.infrastructure;

import io.github.czeffik.events.infrastructure.app.AppInfrastructureConfiguration;
import io.github.czeffik.events.infrastructure.rest.RestInfrastructureConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Clock;

@Import({
    AppInfrastructureConfiguration.class,
    RestInfrastructureConfiguration.class
})
@Configuration
public class InfrastructureConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
