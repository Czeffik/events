package io.github.czeffik.events.interfaces.kafka

import io.github.czeffik.events.domain.information.InformationEventPublisher
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class InformationConsumerTestConfig {
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationEventPublisher informationEventPublisherMock() {
        return factory.Mock(InformationEventPublisher)
    }
}
