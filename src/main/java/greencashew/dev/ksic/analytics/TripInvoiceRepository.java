package greencashew.dev.ksic.analytics;

import static greencashew.dev.ksic.analytics.AnalyticsConfiguration.CACHE_NAME;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = CACHE_NAME)
interface TripInvoiceRepository extends IgniteRepository<TripInvoice, Long> {

}
