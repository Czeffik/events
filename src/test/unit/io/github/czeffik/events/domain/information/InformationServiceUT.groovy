package io.github.czeffik.events.domain.information

import io.github.czeffik.events.TimeHelper
import io.github.czeffik.events.domain.information.events.InformationEventCreation
import io.github.czeffik.events.domain.information.events.PriceEnrichedEvent
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

    def 'when receive start processing event should enrich price and publish price enriched event'() {
        given:
            def event = createStartProcessingEvent()
        and:
            def informationPrice = createInformationPrice(event.information.id)
        when:
            service.enrichPrice(event)
        then:
            1 * priceEnricher.enrich(event.information.id) >> informationPrice
        and:
            1 * publisher.publish({ PriceEnrichedEvent published ->
                assert published.information.id == event.information.id
                assert published.information.name == event.information.name
                assert published.information.description == event.information.description
                assert published.information.price == informationPrice.price

                assert published.timestamp == TimeHelper.FIXED_TIMESTAMP
                assert published.id
                assert published.id != event.id
                return published
            })
    }

    def 'when receive start processing event should throw exception when information price id is not same as information id from event'() {
        given:
            def event = createStartProcessingEvent()
        and:
            def informationPrice = createInformationPrice()
        when:
            service.enrichPrice(event)
        then:
            1 * priceEnricher.enrich(event.information.id) >> informationPrice
        and:
            InformationPriceEnricher.DataInconsistenciesException ex = thrown()
            ex.message == "Information price id: ${informationPrice.id} is not equal information id: ${event.information.id}!"
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
