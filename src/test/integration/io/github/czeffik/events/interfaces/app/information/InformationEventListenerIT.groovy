package io.github.czeffik.events.interfaces.app.information

import io.github.czeffik.events.domain.information.InformationEventStore
import io.github.czeffik.events.domain.information.InformationService
import io.github.czeffik.events.domain.information.events.InformationEvent
import io.github.czeffik.events.domain.information.events.InformationEventCreation
import io.github.czeffik.events.infrastructure.MockedDomainTestConfig
import io.github.czeffik.events.infrastructure.MockedInfrastructureTestConfig
import io.github.czeffik.events.infrastructure.rest.emitter.EmitterStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.util.function.Consumer

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
    EmitterStore<InformationEvent> emitterStoreMock

    @Autowired
    ApplicationEventPublisher applicationEventPublisher

    Set<Consumer<InformationEvent>> EMITTERS = [Mock(Consumer), Mock(Consumer), Mock(Consumer)]

    def 'when receive information start processing event then should enrich price and store information start processing event'() {
        given:
            def startProcessingEvent = createStartProcessingEvent()
        when:
            applicationEventPublisher.publishEvent(startProcessingEvent)
        then:
            1 * informationServiceMock.enrichPrice(startProcessingEvent)
        and:
            1 * informationEventStoreMock.store(startProcessingEvent)
        and:
            1 * emitterStoreMock.getAll() >> EMITTERS
        and:
            EMITTERS.each { 1 * it.accept(startProcessingEvent) }
        and:
            0 * _
    }

    def 'when receive price enriched event then should store price enriched event'() {
        given:
            def priceEnrichedEvent = createPriceEnrichedEvent()
        when:
            applicationEventPublisher.publishEvent(priceEnrichedEvent)
        then:
            1 * informationEventStoreMock.store(priceEnrichedEvent)
        and:
            1 * emitterStoreMock.getAll() >> EMITTERS
        and:
            EMITTERS.each { 1 * it.accept(priceEnrichedEvent) }
        and:
            0 * _
    }

    def 'when receive FAKE event then should do nothing'() {
        given:
            def fakeEvent = createFakeEvent()
        when:
            applicationEventPublisher.publishEvent(fakeEvent)
        then:
            1 * emitterStoreMock.getAll() >> EMITTERS
        and:
            EMITTERS.each { 1 * it.accept(fakeEvent) }
        and:
            0 * _
    }

}
