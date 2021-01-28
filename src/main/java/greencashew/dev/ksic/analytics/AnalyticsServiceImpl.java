package greencashew.dev.ksic.analytics;

import static java.lang.String.format;

import greencashew.dev.ksic.dto.TripInvoiceDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
class AnalyticsServiceImpl implements AnalyticsService {

    private final TripInvoiceFactory tripInvoiceFactory;
    private final TripInvoiceRepository repository;

    @Override
    public void saveOrUpdate(final TripInvoiceDto tripInvoiceDto) {
        final TripInvoice invoiceEntity = tripInvoiceFactory.create(tripInvoiceDto);

        repository.save(invoiceEntity.getId(), invoiceEntity);
        log.info(format("Invoice with id: %s saved into repository. Amount of saved requests: %s", invoiceEntity.getId(), repository.count()));

    }
}
