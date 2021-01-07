package io.github.czeffik.events.interfaces.app;

import io.github.czeffik.events.interfaces.app.information.InformationEventListenerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    InformationEventListenerConfiguration.class
})
@Configuration
public class AppInterfacesConfiguration {
}
