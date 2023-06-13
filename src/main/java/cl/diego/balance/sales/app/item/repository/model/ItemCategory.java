package cl.diego.balance.sales.app.item.repository.model;

import cl.diego.balance.sales.app.item.dto.CategoryDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table( name = "item_category" )
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public ItemCategory( CategoryDto categoryDto ) {
        this.id          = categoryDto.getId();
        this.description = categoryDto.getDescription();
    }

    public CategoryDto toItemCategory() {
        return CategoryDto.builder()
                .id( this.id )
                .description( this.description )
                .build();
    }

}
