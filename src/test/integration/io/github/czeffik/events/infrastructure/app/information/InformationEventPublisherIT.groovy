package io.github.czeffik.events.infrastructure.app.information

import io.github.czeffik.events.domain.information.InformationEventPublisher
import io.github.czeffik.events.domain.information.events.InformationEvent
import io.github.czeffik.events.domain.information.events.InformationEventCreation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.mock.DetachedMockFactory

@ActiveProfiles(['test'])
@SpringBootTest(
    classes = [
        InformationEventPublisherConfiguration,
        InformationEventPublisherIT.TestConfig
    ]
)
class InformationEventPublisherIT extends Specification implements InformationEventCreation {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher
    @Autowired
    InformationEventPublisher informationEventPublisher

    def 'should publish information event'() {
        given:
            def event = createPriceEnrichedEvent()
        when:
            informationEventPublisher.publish(event)
        then:
            1 * applicationEventPublisher.publishEvent(event)
    }

    def 'should publish multiple information events'() {
        given:
            def events = [
                createPriceEnrichedEvent(),
                createFakeEvent(),
                createStartProcessingEvent()
            ]
        when:
            informationEventPublisher.publish(events)
        then:
            events.size() * applicationEventPublisher.publishEvent({ InformationEvent event ->
                assert events.contains(event)
                return event
            })
    }


    //TODO: Consider doing it somehow better? Maybe create test listener?
    @TestConfiguration
    static class TestConfig {
        DetachedMockFactory factory = new DetachedMockFactory()

        @Bean
        @Primary
        ApplicationEventPublisher applicationEventPublisher() {
            return factory.Mock(ApplicationEventPublisher)
        }
    }
}
