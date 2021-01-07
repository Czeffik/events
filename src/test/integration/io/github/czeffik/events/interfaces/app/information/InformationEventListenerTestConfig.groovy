package io.github.czeffik.events.interfaces.app.information

import io.github.czeffik.events.domain.information.InformationService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class InformationEventListenerTestConfig {
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationService informationServiceMock() {
        return factory.Mock(InformationService)
    }
}