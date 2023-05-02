package cl.diego.balance.sales.app.sale.dto.request;

import java.math.BigDecimal;

public record SaleItemRequest(String sku, Double quantity, BigDecimal total) {

}
