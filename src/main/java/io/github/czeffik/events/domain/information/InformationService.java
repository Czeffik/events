package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;

public interface InformationService {
    void enrichPrice(StartProcessingEvent event);

    @Getter
    @ToString
    @Builder
    class Information {
        private final @NonNull String id;
        private final @NonNull String name;
        private final @NonNull String description;
    }

    @Getter
    @ToString
    @Builder
    class InformationWithPrice {
        private final @NonNull String id;
        private final @NonNull String name;
        private final @NonNull String description;
        private final @NonNull BigDecimal price;
    }
}
