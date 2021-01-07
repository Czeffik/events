package io.github.czeffik.events.domain.information.events

import java.time.Instant

trait InformationEventCreation {

    InformationEvent createFakeEvent() {
        return new FakeEvent()
    }

    InformationUpdateReceivedEvent createUpdateReceivedEvent() {
        return new InformationUpdateReceivedEvent(
            Instant.now(),
            'UPDATE_RECEIVED_ID',
            'UPDATE_RECEIVED_NAME',
            'UPDATE_RECEIVED_DESCRIPTION'
        )
    }

    InformationWithPriceEvent createWithPriceEvent() {
        return new InformationWithPriceEvent(
            Instant.now(),
            'UPDATE_RECEIVED_ID',
            'UPDATE_RECEIVED_NAME',
            'UPDATE_RECEIVED_DESCRIPTION',
            BigDecimal.TEN
        )
    }

    InformationWithPriceEvent createWithPriceEvent(InformationUpdateReceivedEvent event) {
        return new InformationWithPriceEvent(
            Instant.now(),
            event.id,
            event.name,
            event.description,
            BigDecimal.TEN
        )
    }

    static class FakeEvent implements InformationEvent {

        @Override
        Instant getTimestamp() {
            return Instant.now()
        }
    }
}
