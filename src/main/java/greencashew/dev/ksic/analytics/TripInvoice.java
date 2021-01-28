package greencashew.dev.ksic.analytics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

@Data
@Builder
class TripInvoice implements Serializable {

    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField
    private String name;
    @QuerySqlField
    private Timestamp date;
    @QuerySqlField
    private String address;
    @QuerySqlField
    private String startGate;
    @QuerySqlField
    private String exitGate;
    @QuerySqlField
    private BigDecimal priceNet;
    @QuerySqlField
    private Short priceTaxPercentage;
    @QuerySqlField
    private BigDecimal priceTotal;
}
