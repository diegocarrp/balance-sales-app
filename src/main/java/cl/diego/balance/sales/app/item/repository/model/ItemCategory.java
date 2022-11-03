package cl.diego.balance.sales.app.item.repository.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("item_categories")
public class ItemCategory {

    @Id
    private String id;
    private String description;

}
