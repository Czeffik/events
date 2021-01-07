package io.github.czeffik.events.domain.information

import io.github.czeffik.events.TimeHelper
import io.github.czeffik.events.domain.information.events.InformationEventCreation
import io.github.czeffik.events.domain.information.events.InformationWithPriceEvent
import spock.lang.Specification

class InformationServiceUT extends Specification implements InformationEventCreation, InformationPriceCreation {

    InformationPriceEnricher priceEnricher = Mock()
    InformationEventPublisher publisher = Mock()
    InformationService service

    def setup() {
        service = InformationServiceFactory.create(
            TimeHelper.FIXED_CLOCK,
            priceEnricher,
            publisher
        )
    }

    def 'when receive update received event should enrich price and publish event with price'() {
        given:
            def event = createUpdateReceivedEvent()
        and:
            def informationPrice = createInformationPrice(event.id)
        when:
            service.enrichPrice(event)
        then:
            1 * priceEnricher.enrich(event.id) >> informationPrice
            1 * publisher.publish({ InformationWithPriceEvent published ->
                assert published.id == event.id
                assert published.name == event.name
                assert published.description == event.description
                assert published.price == informationPrice.price
                assert published.timestamp == TimeHelper.FIXED_TIMESTAMP
                assert published.eventId
                assert published.eventId != event.eventId
                return published
            })
    }

    def 'when receive update received event should throw exception when information price id is not same as event id'() {
        given:
            def event = createUpdateReceivedEvent()
        and:
            def informationPrice = createInformationPrice()
        when:
            service.enrichPrice(event)
        then:
            1 * priceEnricher.enrich(event.id) >> informationPrice
        and:
            InformationPriceEnricher.DataInconsistenciesException ex = thrown()
            ex.message == "Information price id: ${informationPrice.id} is not equal event id: ${event.id}!"
    }

    def 'should throw IllegalArgumentException when creating InformationService and clock is null'() {
        when:
            InformationServiceFactory.create(null, priceEnricher, publisher)
        then:
            IllegalArgumentException ex = thrown()
            ex.message == 'clock is marked non-null but is null'
    }

    def 'should throw IllegalArgumentException when creating InformationService and priceEnricher is null'() {
        when:
            InformationServiceFactory.create(TimeHelper.FIXED_CLOCK, null, publisher)
        then:
            IllegalArgumentException ex = thrown()
            ex.message == 'priceEnricher is marked non-null but is null'
    }

    def 'should throw IllegalArgumentException when creating InformationService and publisher is null'() {
        when:
            InformationServiceFactory.create(TimeHelper.FIXED_CLOCK, priceEnricher, null)
        then:
            IllegalArgumentException ex = thrown()
            ex.message == 'publisher is marked non-null but is null'
    }
}
