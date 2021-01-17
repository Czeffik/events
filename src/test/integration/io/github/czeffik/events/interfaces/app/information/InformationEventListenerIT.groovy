package io.github.czeffik.events.interfaces.app.information

import io.github.czeffik.events.domain.information.InformationEventStore
import io.github.czeffik.events.domain.information.InformationService
import io.github.czeffik.events.domain.information.events.InformationEventCreation
import io.github.czeffik.events.infrastructure.MockedDomainTestConfig
import io.github.czeffik.events.infrastructure.MockedInfrastructureTestConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(['test'])
@SpringBootTest(
    classes = [
        InformationEventListenerConfiguration,
        MockedDomainTestConfig,
        MockedInfrastructureTestConfig
    ]
)
class InformationEventListenerIT extends Specification implements InformationEventCreation {

    @Autowired
    InformationService informationServiceMock

    @Autowired
    InformationEventStore informationEventStoreMock

    @Autowired
    ApplicationEventPublisher applicationEventPublisher

    def 'when receive information start processing event then should enrich price and store information start processing event'() {
        given:
            def startProcessingEvent = createStartProcessingEvent()
        when:
            applicationEventPublisher.publishEvent(startProcessingEvent)
        then:
            1 * informationServiceMock.enrichPrice(startProcessingEvent)
        and:
            1 * informationEventStoreMock.store(startProcessingEvent)
    }

    def 'when receive price enriched event then should store price enriched event'() {
        given:
            def priceEnrichedEvent = createPriceEnrichedEvent()
        when:
            applicationEventPublisher.publishEvent(priceEnrichedEvent)
        then:
            1 * informationEventStoreMock.store(priceEnrichedEvent)
    }

    def 'when receive FAKE event then should do nothing'() {
        given:
            def fakeEvent = createFakeEvent()
        when:
            applicationEventPublisher.publishEvent(fakeEvent)
        then:
            0 * _
    }

}
