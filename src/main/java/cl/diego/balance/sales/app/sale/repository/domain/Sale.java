package cl.diego.balance.sales.app.sale.repository.domain;

import cl.diego.balance.sales.app.sale.dto.PaymentDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SaleItemDto;
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
                .items( this.items.stream()
                        .map( i -> SaleItemDto.builder()
                                .id( i.getId() )
                                .sku( i.getSku() )
                                .quantity( i.getQuantity() )
                                .total( i.getTotal() )
                                .build() )
                        .collect( Collectors.toList()) )
                .payments( this.payments.stream()
                        .map( p -> PaymentDto.builder()
                                .paymentMethodId( p.getPaymentMethod().getId() )
                                .amount( p.getAmount() )
                                .build() )
                        .collect( Collectors.toList()) )
                .build( );
    }
}
