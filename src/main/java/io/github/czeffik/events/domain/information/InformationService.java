package io.github.czeffik.events.domain.information;

import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent;
import io.github.czeffik.events.domain.information.events.InformationWithPriceEvent;

public interface InformationService {
    InformationWithPriceEvent enrichPrice(InformationUpdateReceivedEvent event);
}
