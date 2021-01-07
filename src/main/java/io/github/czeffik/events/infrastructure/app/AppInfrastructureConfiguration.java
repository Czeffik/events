package io.github.czeffik.events.infrastructure.app;

import io.github.czeffik.events.infrastructure.app.information.InformationEventPublisherConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    InformationEventPublisherConfiguration.class
})
@Configuration
public class AppInfrastructureConfiguration {

}
