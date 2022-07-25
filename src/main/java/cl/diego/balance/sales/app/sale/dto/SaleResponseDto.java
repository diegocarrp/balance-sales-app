package cl.diego.balance.sales.app.sale.dto;

import cl.diego.balance.sales.app.sale.client.dto.CustomerDto;
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
public class SaleResponseDto {

    private Long              id;
    private CustomerDto       customer;
    private CustomerDto       cashier;
    private BigDecimal        totalAmount;
    private LocalDateTime     datetime;
    private List<SaleItemDto> items;
    private List<PaymentDto>  payments;
}
