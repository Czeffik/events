package io.github.czeffik.events.domain.information

trait InformationCreation {

    InformationService.Information createInformation() {
        return InformationService.Information.builder()
            .id('INFORMATION_ID')
            .name('INFORMATION_NAME')
            .description('INFORMATION_DESCRIPTION')
            .build()
    }

    InformationService.InformationWithPrice createInformationWithPrice() {
        return InformationService.InformationWithPrice.builder()
            .id('INFORMATION_WITH_PRICE_ID')
            .name('INFORMATION_WITH_PRICE_NAME')
            .description('INFORMATION_WITH_PRICE_DESCIRPTION')
            .price(BigDecimal.TEN)
            .build()
    }
}
