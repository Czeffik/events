package io.github.czeffik.events.domain.information

trait InformationPriceCreation {

    InformationPriceEnricher.InformationPrice createInformationPrice() {
        return createInformationPrice('INFORMATION_PRICE_ID')
    }

    InformationPriceEnricher.InformationPrice createInformationPrice(String id) {
        return new InformationPriceEnricher.InformationPrice(
            id,
            BigDecimal.TEN
        )
    }
}
