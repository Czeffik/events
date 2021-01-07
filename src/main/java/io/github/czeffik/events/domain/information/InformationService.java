package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;

public interface InformationService {
    void enrichPrice(InformationUpdateReceivedEvent event);
}
