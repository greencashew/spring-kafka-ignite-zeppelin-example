package greencashew.dev.ksic.consumer;

import java.util.function.Consumer;

import greencashew.dev.ksic.analytics.AnalyticsService;
import greencashew.dev.ksic.dto.TripInvoiceDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class TripInvoicesConsumerConfiguration {

    @Bean
    public Consumer<KStream<String, TripInvoiceDto>> carInvoicesConsumer(final AnalyticsService analyticsService) {
        return stream -> stream
                .foreach((key, value) -> updateAnalyticsDB(analyticsService, value));
    }

    private void updateAnalyticsDB(final AnalyticsService analyticsService, final TripInvoiceDto value) {
        log.info("Consumed: " + value);
        analyticsService.saveOrUpdate(value);
    }
}