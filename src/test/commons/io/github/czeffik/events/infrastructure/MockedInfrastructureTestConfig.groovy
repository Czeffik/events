package io.github.czeffik.events.infrastructure

import io.github.czeffik.events.TimeHelper
import io.github.czeffik.events.domain.information.InformationEventPublisher
import io.github.czeffik.events.domain.information.InformationEventStore
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

import java.time.Clock

@TestConfiguration
class MockedInfrastructureTestConfig {
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationEventPublisher informationEventPublisherMock() {
        return factory.Mock(InformationEventPublisher)
    }

    @Bean
    Clock fixedClock() {
        return TimeHelper.FIXED_CLOCK
    }

    @Bean
    InformationEventStore informationEventStoreMock() {
        return factory.Mock(InformationEventStore)
    }
}
