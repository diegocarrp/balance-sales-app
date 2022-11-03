package cl.diego.balance.sales.app.item.repository.model;

import cl.diego.balance.sales.app.item.dto.ItemTypeDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("item_types")
public class ItemType {

    @Id
    private String id;
    private String description;

}
