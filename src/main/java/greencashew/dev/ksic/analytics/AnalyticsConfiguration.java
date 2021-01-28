package greencashew.dev.ksic.analytics;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cluster.ClusterState;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableIgniteRepositories
class AnalyticsConfiguration {

    static final String CACHE_NAME = "PUBLIC";

    @Bean
    public AnalyticsService analyticsService(final TripInvoiceRepository repository) {
        return new AnalyticsServiceImpl(new TripInvoiceFactory(), repository);
    }

    @Bean
    public Ignite igniteInstance() {
        final IgniteConfiguration config = new IgniteConfiguration();
        config.setDataStorageConfiguration(getStorageConfiguration());
        config.setIgniteInstanceName("InvoicesAnalyticsDB");
        config.setPeerClassLoadingEnabled(true);
        config.setCacheConfiguration(getCacheConfiguration());
        final Ignite ignite = Ignition.start(config);
        ignite.cluster().state(ClusterState.ACTIVE);
        return ignite;
    }

    private DataStorageConfiguration getStorageConfiguration() {
        final DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        // Data would be saved on disk
        storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
        log.info("Storage Persistence Enabled!");
        return storageCfg;
    }

    private CacheConfiguration getCacheConfiguration() {
        final CacheConfiguration config = new CacheConfiguration(CACHE_NAME);
        config.setCacheMode(CacheMode.REPLICATED);

        // Setting SQL schema for the cache.
        config.setIndexedTypes(Long.class, TripInvoice.class);
        return config;
    }
}
