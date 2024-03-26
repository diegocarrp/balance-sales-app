package cl.diego.balance.sales.app.sale.repository.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "public", name = "sale_item")
@ToString
public class SaleItem {

    @Id
    private Long id;
    private Long saleId;
    private String sku;
    private Double quantity;
    private BigDecimal total;

}
