package io.github.czeffik.events.interfaces.kafka.information;

import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class InformationDto {
    private String id;
    private String name;
    private String description;

    public InformationEvent toStartProcessingEvent(final Instant timestamp) {
        return StartProcessingEvent.builder()
            .timestamp(timestamp)
            .information(InformationService.Information
                .builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .build())
            .build();
    }
}
