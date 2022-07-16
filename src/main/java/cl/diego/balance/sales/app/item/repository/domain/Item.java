package cl.diego.balance.sales.app.item.repository.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "item" )
@ToString
public class Item {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Long categoryId;
    private Long itemTypeId;

}
