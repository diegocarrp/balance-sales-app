package cl.diego.balance.sales.app.sale.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemDto {

    private Long       id;
    private String     sku;
    private Double     quantity;
    private BigDecimal total;

}
