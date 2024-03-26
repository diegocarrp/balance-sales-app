package cl.diego.balance.sales.app.sale.dto;

import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public SaleDto( SaleRequest request ) {
        this.cashierId   = request.cashierId( );
        this.customerId  = request.customerId( );
        this.totalAmount = request.totalAmount( );
        this.datetime    = request.datetime( );
        this.items       = request.items( ).stream( ).map( itm -> SaleItemDto.builder( )
                .sku( itm.sku( ) )
                .total( itm.total( ) )
                .quantity( itm.quantity( ) )
                .build( ) ).collect( Collectors.toList( ) );
        this.payments    = request.payments( ).stream( ).map( payment -> PaymentDto.builder( )
                .amount( payment.amount( ) )
                .id( payment.id( ) )
                .build( ) ).collect( Collectors.toList( ) );
    }


}
