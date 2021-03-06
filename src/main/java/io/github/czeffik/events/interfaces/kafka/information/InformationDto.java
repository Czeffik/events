package io.github.czeffik.events.interfaces.kafka.information;

import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class InformationDto {
    private String id;
    private String name;
    private String description;

    public InformationEvent toInformationUpdateReceivedEvent(final Instant timestamp) {
        return InformationUpdateReceivedEvent.builder()
            .timestamp(timestamp)
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .build();
    }
}
