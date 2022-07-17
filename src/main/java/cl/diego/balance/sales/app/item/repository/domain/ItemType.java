package cl.diego.balance.sales.app.item.repository.domain;

import cl.diego.balance.sales.app.item.dto.ItemTypeDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "item_type" )
@ToString
public class ItemType {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;
    private String description;

    public ItemType( ItemTypeDto itemType ) {
        this.description = itemType.getDescription();
    }

    public ItemTypeDto toItemType( ) {
        return ItemTypeDto.builder( )
                .id( this.id )
                .description( this.description )
                .build( );
    }
}
