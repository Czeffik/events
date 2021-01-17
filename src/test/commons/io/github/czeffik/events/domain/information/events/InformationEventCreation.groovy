package io.github.czeffik.events.domain.information.events

import io.github.czeffik.events.domain.information.InformationCreation

import java.time.Instant

trait InformationEventCreation implements InformationCreation {

    InformationEvent createFakeEvent() {
        return new FakeEvent()
    }

    StartProcessingEvent createStartProcessingEvent() {
        return StartProcessingEvent.builder()
            .timestamp(Instant.now())
            .id('START_PROCESSING_EVENT_ID')
            .information(createInformation())
            .build()
    }

    PriceEnrichedEvent createPriceEnrichedEvent() {
        return PriceEnrichedEvent.builder()
            .timestamp(Instant.now())
            .id('PRICE_ENRICHED_EVENT_ID')
            .information(createInformationWithPrice())
            .build()
    }

    static class FakeEvent implements InformationEvent {

        @Override
        Instant getTimestamp() {
            return Instant.now()
        }

        @Override
        String getId() {
            return 'FAKE_EVENT_ID'
        }
    }
}
