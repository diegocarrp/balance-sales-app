package cl.diego.balance.sales.app.sale.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

    private Long              id;
    private Long              customerId;
    private Long              cashierId;
    private BigDecimal        totalAmount;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime     datetime;
    private List<SaleItemDto> items;
    private List<PaymentDto>  payments;
}
