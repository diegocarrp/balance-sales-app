package cl.diego.balance.sales.app.sale.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDto {

    private Long              id;
    private Long              clientId;
    private Long              cashierId;
    private LocalDateTime     transactionDatetime;
    private List<SaleItemDto> items;
    private List<PaymentDto>  payments;
}
