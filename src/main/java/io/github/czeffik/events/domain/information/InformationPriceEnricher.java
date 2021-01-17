package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.PriceEnrichedEvent;
import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
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

        public PriceEnrichedEvent toPriceEnrichedEvent(
            final StartProcessingEvent event,
            final Instant timestamp
        ) {
            if (idsAreDifferent(event)) {
                throw new DataInconsistenciesException(
                    String.format("Information price id: %s is not equal information id: %s!",
                        this.id, event.getInformation().getId())
                );
            }

            return PriceEnrichedEvent.builder()
                .timestamp(timestamp)
                .information(InformationService.InformationWithPrice.builder()
                    .id(event.getInformation().getId())
                    .description(event.getInformation().getDescription())
                    .name(event.getInformation().getName())
                    .price(this.price)
                    .build())
                .build();
        }

        private boolean idsAreDifferent(StartProcessingEvent event) {
            return !event.getInformation().getId().equals(this.id);
        }
    }

    class DataInconsistenciesException extends RuntimeException {

        public DataInconsistenciesException(String msg) {
            super(msg);
        }
    }
}
