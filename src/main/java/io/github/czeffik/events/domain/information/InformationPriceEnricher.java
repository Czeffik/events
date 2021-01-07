package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import io.github.czeffik.events.domain.information.events.InformationWithPriceEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

public interface InformationPriceEnricher {

    InformationPrice enrich(String id);

    @Getter
    @ToString
    @RequiredArgsConstructor
    class InformationPrice {
        private final String id;
        private final BigDecimal price;

        public InformationWithPriceEvent toInformationWithPriceEvent(
            final InformationUpdateReceivedEvent event,
            final Instant timestamp
        ) {
            if (idsAreDifferent(event)) {
                throw new DataInconsistenciesException(
                    String.format("Information price id: %s is not equal event id: %s!", this.id, event.getId())
                );
            }

            return InformationWithPriceEvent.builder()
                .timestamp(timestamp)
                .id(event.getId())
                .description(event.getDescription())
                .name(event.getName())
                .price(this.price)
                .build();
        }

        private boolean idsAreDifferent(InformationUpdateReceivedEvent event) {
            return !event.getId().equals(this.id);
        }
    }

    class DataInconsistenciesException extends RuntimeException {

        public DataInconsistenciesException(String msg) {
            super(msg);
        }
    }
}
