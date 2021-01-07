package io.github.czeffik.events.domain.information.events

import java.time.Instant

trait InformationEventCreation {

    InformationEvent createFakeEvent() {
        return new FakeEvent()
    }

    InformationUpdateReceivedEvent createUpdateReceivedEvent() {
        return InformationUpdateReceivedEvent.builder()
            .timestamp(Instant.now())
            .id('UPDATE_RECEIVED_ID')
            .name('UPDATE_RECEIVED_NAME')
            .description('UPDATE_RECEIVED_DESCRIPTION')
            .build()
    }

    InformationWithPriceEvent createWithPriceEvent() {
        return InformationWithPriceEvent.builder()
            .timestamp(Instant.now())
            .id('INFORMATION_WITH_PRICE_ID')
            .name('INFORMATION_WITH_PRICE_NAME')
            .description('INFORMATION_WITH_PRICE_DESCRIPTION')
            .price(BigDecimal.TEN)
            .build()
    }

    InformationWithPriceEvent createWithPriceEvent(InformationUpdateReceivedEvent event) {
        return InformationWithPriceEvent.builder()
            .timestamp(Instant.now())
            .id(event.id)
            .name(event.name)
            .description(event.description)
            .price(BigDecimal.TEN)
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

        @Override
        String getEventId() {
            return UUID.randomUUID().toString()
        }
    }
}
