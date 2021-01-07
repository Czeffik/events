package io.github.czeffik.events.infrastructure.db;

import io.github.czeffik.events.infrastructure.db.information.InformationEventStoreConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    InformationEventStoreConfiguration.class
})
@Configuration
public class DbConfiguration {
}
