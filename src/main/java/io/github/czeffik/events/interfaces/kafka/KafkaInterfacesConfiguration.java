package io.github.czeffik.events.interfaces.kafka;

import io.github.czeffik.events.interfaces.kafka.information.InformationListenerConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@ImportAutoConfiguration({
    KafkaAutoConfiguration.class
})
@Import({
    InformationListenerConfiguration.class
})
@EnableKafka
public class KafkaInterfacesConfiguration {
}
