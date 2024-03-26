package cl.diego.balance.sales.app.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailItemDto {

    private String category;
    private BigDecimal salesAmount;

}
