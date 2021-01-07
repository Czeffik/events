package io.github.czeffik.events.infrastructure.rest;

import io.github.czeffik.events.domain.information.InformationPriceEnricher;

import java.math.BigDecimal;
import java.util.Random;

class FakeInformationPriceEnricher implements InformationPriceEnricher {
    private final Random random = new Random();

    @Override
    public InformationPrice enrich(String id) {
        return new InformationPrice(id, generateRandomPrice());
    }

    private BigDecimal generateRandomPrice() {
        return BigDecimal.valueOf(random.nextInt(), 4);
    }
}
