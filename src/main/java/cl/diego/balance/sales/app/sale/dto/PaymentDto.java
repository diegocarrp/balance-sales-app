package cl.diego.balance.sales.app.sale.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {

    private Long paymentMethodId;
    private BigDecimal amount;
}
