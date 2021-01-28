package greencashew.dev.ksic.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private BigDecimal net;
    private Short taxPercentage;
    private BigDecimal total;
}