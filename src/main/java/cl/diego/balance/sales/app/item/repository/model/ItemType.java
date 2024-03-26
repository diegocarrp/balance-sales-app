package cl.diego.balance.sales.app.item.repository.model;

import cl.diego.balance.sales.app.item.dto.ItemTypeDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "item_type")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public ItemType( ItemTypeDto itemTypeDto ) {
        this.id          = itemTypeDto.getId();
        this.description = itemTypeDto.getDescription();
    }

    public ItemTypeDto toItemType() {
        return ItemTypeDto.builder()
                .id( this.id )
                .description( this.description )
                .build();
    }

}
