package io.github.czeffik.events.interfaces.kafka.information;

import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InformationDto {
    private String id;
    private String name;
    private String description;

    public InformationEvent toInformationUpdateReceivedEvent() {
        return new InformationUpdateReceivedEvent();
    }
}
