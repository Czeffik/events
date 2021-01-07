package io.github.czeffik.events.infrastructure.app.listener

import io.github.czeffik.events.domain.information.InformationEventPublisher
import io.github.czeffik.events.domain.information.InformationService
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent
import io.github.czeffik.events.domain.information.events.InformationWithPriceEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.Instant

@ActiveProfiles(['test'])
@SpringBootTest(
    classes = [
        InformationEventListenerConfiguration,
        InformationEventListenerTestConfig
    ]
)
class InformationEventListenerIT extends Specification {

    @Autowired
    InformationEventPublisher informationEventPublisherMock

    @Autowired
    InformationService informationServiceMock

    @Autowired
    ApplicationEventPublisher applicationEventPublisher

    def 'when receive information update received event then should enrich price and publish information with price event'(){
        given:
            def updateEvent = new InformationUpdateReceivedEvent(
                Instant.now(),
                'id',
                'name',
                'description'
            )
        and:
            def eventWithPrice = new InformationWithPriceEvent(
                Instant.now(),
                'id',
                'name',
                'description',
                BigDecimal.TEN
            )
        when:
            applicationEventPublisher.publishEvent(updateEvent)
        then:
            1 * informationServiceMock.enrichPrice(updateEvent) >> eventWithPrice
            1 * informationEventPublisherMock.publish(eventWithPrice)
    }

}
