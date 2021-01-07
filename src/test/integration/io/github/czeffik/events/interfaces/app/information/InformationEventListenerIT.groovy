package io.github.czeffik.events.interfaces.app.information

import io.github.czeffik.events.domain.information.InformationService
import io.github.czeffik.events.domain.information.events.InformationEventCreation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(['test'])
@SpringBootTest(
    classes = [
        InformationEventListenerConfiguration,
        InformationEventListenerTestConfig
    ]
)
class InformationEventListenerIT extends Specification implements InformationEventCreation {

    @Autowired
    InformationService informationServiceMock

    @Autowired
    ApplicationEventPublisher applicationEventPublisher

    def 'when receive information update received event then should enrich price'() {
        given:
            def updateEvent = createUpdateReceivedEvent()
        when:
            applicationEventPublisher.publishEvent(updateEvent)
        then:
            1 * informationServiceMock.enrichPrice(updateEvent)
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
