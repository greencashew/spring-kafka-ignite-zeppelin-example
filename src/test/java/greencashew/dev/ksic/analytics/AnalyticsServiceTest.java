package greencashew.dev.ksic.analytics;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;

import greencashew.dev.ksic.dto.Price;
import greencashew.dev.ksic.dto.TripInvoiceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    private TripInvoiceRepository repository;
    @Captor
    private ArgumentCaptor<TripInvoice> invoiceEntity;

    private AnalyticsServiceImpl analyticsService;

    @BeforeEach
    void setUp() {
        analyticsService = new AnalyticsServiceImpl(new TripInvoiceFactory(), repository);
    }

    @Test
    void shouldSaveInvoiceToRepository() {
        //given
        final Price SOME_PRICE = new Price(new BigDecimal(20), (short) 12, new BigDecimal(22));
        final long SOME_INVOICE_ID = 532L;
        final String SOME_NAME = "Jan Kowalski";
        final String SOME_ADDRESS = "Kwiatowa 4";
        final String SOME_START_GATE = "Wrocław";
        final String SOME_EXIT_GATE = "Kraków";
        final LocalDateTime SOME_DATE_TIME = LocalDateTime.parse("2011-12-03T10:15:30");
        final TripInvoiceDto SOME_TRIP_INVOICE_DTO = new TripInvoiceDto(SOME_INVOICE_ID, SOME_NAME, SOME_DATE_TIME, SOME_ADDRESS, SOME_START_GATE, SOME_EXIT_GATE, SOME_PRICE);

        //when
        analyticsService.saveOrUpdate(SOME_TRIP_INVOICE_DTO);

        //then
        then(repository).should().save(eq(SOME_TRIP_INVOICE_DTO.getId()), invoiceEntity.capture());
        final TripInvoice entityValue = invoiceEntity.getValue();
        assertEquals(SOME_INVOICE_ID, entityValue.getId());
        assertEquals(SOME_NAME, entityValue.getName());
        assertEquals(SOME_DATE_TIME, entityValue.getDate().toLocalDateTime());
        assertEquals(SOME_ADDRESS, entityValue.getAddress());
        assertEquals(SOME_START_GATE, entityValue.getStartGate());
        assertEquals(SOME_EXIT_GATE, entityValue.getExitGate());
        assertEquals(SOME_PRICE.getNet(), entityValue.getPriceNet());
        assertEquals(SOME_PRICE.getTaxPercentage(), entityValue.getPriceTaxPercentage());
        assertEquals(SOME_PRICE.getTotal(), entityValue.getPriceTotal());

    }
}