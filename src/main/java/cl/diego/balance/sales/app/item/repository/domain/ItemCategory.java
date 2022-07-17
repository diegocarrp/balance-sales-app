package cl.diego.balance.sales.app.item.repository.domain;

import cl.diego.balance.sales.app.item.dto.CategoryDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "item_category" )
@ToString
public class ItemCategory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;
    private String description;

    public ItemCategory( CategoryDto category ) {
        this.description = category.getDescription();
    }

    public CategoryDto toItemType( ) {
        return CategoryDto.builder( )
                .id( this.id )
                .description( this.description )
                .build( );

    }
}
