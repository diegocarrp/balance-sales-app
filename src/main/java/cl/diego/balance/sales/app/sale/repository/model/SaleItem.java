package cl.diego.balance.sales.app.sale.repository.model;

import cl.diego.balance.sales.app.sale.dto.SaleItemDto;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "sale_item" )
@ToString
public class SaleItem {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long       id;
    private String     sku;
    @ToStringExclude
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "sale_id" )
    private Sale       sale;
    private Double     quantity;
    private BigDecimal total;

    public SaleItem( SaleItemDto saleItemDto,
                     Sale sale ) {
        this.sku      = saleItemDto.getSku( );
        this.quantity = saleItemDto.getQuantity( );
        this.total    = saleItemDto.getTotal( );
        this.sale     = sale;
    }
}
