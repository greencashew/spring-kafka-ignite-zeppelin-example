package greencashew.dev.ksic.analytics;

import java.sql.Timestamp;

import greencashew.dev.ksic.dto.Price;
import greencashew.dev.ksic.dto.TripInvoiceDto;

class TripInvoiceFactory {

    TripInvoice create(final TripInvoiceDto tripInvoiceDto) {
        final Price price = tripInvoiceDto.getPrice();
        return TripInvoice.builder()
                .id(tripInvoiceDto.getId())
                .name(tripInvoiceDto.getName())
                .date(Timestamp.valueOf(tripInvoiceDto.getDate()))
                .address(tripInvoiceDto.getAddress())
                .startGate(tripInvoiceDto.getStartGate())
                .exitGate(tripInvoiceDto.getExitGate())
                .priceNet(price.getNet())
                .priceTaxPercentage(price.getTaxPercentage())
                .priceTotal(price.getTotal())
                .build();
    }
}
