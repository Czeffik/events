package io.github.czeffik.events.interfaces.rest.information;

import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.PriceEnrichedEvent;
import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InformationDto {
    private final String id;
    private final String description;
    private final String name;
    private final BigDecimal price;

    public static InformationDto from(InformationEvent event) {
        if (event instanceof StartProcessingEvent) {
            return from((StartProcessingEvent) event);
        }
        if (event instanceof PriceEnrichedEvent) {
            return from((PriceEnrichedEvent) event);
        }
        throw new RuntimeException("");
    }

    public static InformationDto from(StartProcessingEvent event) {
        return new InformationDto(
            event.getInformation().getId(),
            event.getInformation().getDescription(),
            event.getInformation().getName(),
            null
        );
    }

    public static InformationDto from(PriceEnrichedEvent event) {
        return new InformationDto(
            event.getInformation().getId(),
            event.getInformation().getDescription(),
            event.getInformation().getName(),
            event.getInformation().getPrice()
        );
    }
}
