package greencashew.dev.ksic.analytics;

import greencashew.dev.ksic.dto.TripInvoiceDto;

public interface AnalyticsService {
    void saveOrUpdate(TripInvoiceDto tripInvoiceDto);
}
