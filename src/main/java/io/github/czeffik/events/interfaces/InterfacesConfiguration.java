package io.github.czeffik.events.interfaces;

import io.github.czeffik.events.interfaces.app.AppInterfacesConfiguration;
import io.github.czeffik.events.interfaces.kafka.KafkaInterfacesConfiguration;
import io.github.czeffik.events.interfaces.rest.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    RestConfiguration.class,
    KafkaInterfacesConfiguration.class,
    AppInterfacesConfiguration.class
})
@Configuration
public class InterfacesConfiguration {
}
