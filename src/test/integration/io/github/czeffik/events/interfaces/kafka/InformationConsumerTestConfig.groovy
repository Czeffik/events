package io.github.czeffik.events.interfaces.kafka

import io.github.czeffik.events.domain.information.InformationEventPublisher
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@TestConfiguration
class InformationConsumerTestConfig {
    static final Instant FIXED_TIMESTAMP = Instant.parse('2021-01-07T10:00:00Z')
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationEventPublisher informationEventPublisherMock() {
        return factory.Mock(InformationEventPublisher)
    }

    @Bean
    Clock fixedClock(){
        return Clock.fixed(FIXED_TIMESTAMP, ZoneId.of('UTC'))
    }
}
