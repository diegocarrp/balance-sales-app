package cl.diego.balance.sales.app.sale.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleRequest(Long cashierId, Long customerId, BigDecimal totalAmount,
                          @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ) LocalDateTime datetime,
                          List<SaleItemRequest> items, List<PaymentRequest> payments) {


}

