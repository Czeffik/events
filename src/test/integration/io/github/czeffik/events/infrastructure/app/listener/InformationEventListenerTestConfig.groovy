package io.github.czeffik.events.infrastructure.app.listener

import io.github.czeffik.events.domain.information.InformationEventPublisher
import io.github.czeffik.events.domain.information.InformationService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class InformationEventListenerTestConfig {
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationEventPublisher informationEventPublisherMock() {
        return factory.Mock(InformationEventPublisher)
    }

    @Bean
    InformationService informationServiceMock() {
        return factory.Mock(InformationService)
    }
}
