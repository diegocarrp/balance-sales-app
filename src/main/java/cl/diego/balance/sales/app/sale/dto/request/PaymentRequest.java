package cl.diego.balance.sales.app.sale.dto.request;

import java.math.BigDecimal;

public record PaymentRequest(Long id, BigDecimal amount) {
}
