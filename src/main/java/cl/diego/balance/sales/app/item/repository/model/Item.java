package cl.diego.balance.sales.app.item.repository.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("items")
public class Item {

    @Id
    private String     id;
    private String     description;
    private String     sku;
    private BigDecimal price;

}
