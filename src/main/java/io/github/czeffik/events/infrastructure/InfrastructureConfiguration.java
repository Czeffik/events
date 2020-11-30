package io.github.czeffik.events.infrastructure;

import io.github.czeffik.events.infrastructure.app.AppConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    AppConfiguration.class
})
@Configuration
public class InfrastructureConfiguration {

}
