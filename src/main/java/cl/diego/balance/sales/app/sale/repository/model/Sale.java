package cl.diego.balance.sales.app.sale.repository.model;

import cl.diego.balance.sales.app.sale.dto.PaymentDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SaleItemDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "sale" )
@ToString
public class Sale {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long           id;
    private Long           customerId;
    private Long           cashierId;
    private BigDecimal     totalAmount;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime  datetime;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinColumn( name = "sale_id" )
    private List<SaleItem> items;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinColumn( name = "sale_id" )
    private List<Payment>  payments;

    public SaleDto toSale( ) {
        return SaleDto.builder( )
                .id( this.id )
                .customerId( this.customerId )
                .cashierId( this.cashierId )
                .totalAmount( this.totalAmount )
                .datetime( this.datetime )
                .items( this.items.stream( )
                        .map( item -> SaleItemDto.builder( )
                                .id( item.getId( ) )
                                .sku( item.getSku( ) )
                                .quantity( item.getQuantity( ) )
                                .total( item.getTotal( ) )
                                .build( ) )
                        .collect( Collectors.toList( ) ) )
                .payments( this.payments.stream( )
                        .map( payment -> PaymentDto.builder( )
                                .paymentMethodId( payment.getPaymentMethod( ).getId( ) )
                                .amount( payment.getAmount( ) )
                                .build( ) )
                        .collect( Collectors.toList( ) ) )
                .build( );
    }
}
