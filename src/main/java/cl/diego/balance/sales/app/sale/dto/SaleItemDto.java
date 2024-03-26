package cl.diego.balance.sales.app.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemDto {

    private Long       id;
    private String     sku;
    private Double     quantity;
    private BigDecimal total;

}
