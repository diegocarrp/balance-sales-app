package cl.diego.balance.sales.app.item.repository.model;

import cl.diego.balance.sales.app.item.dto.ItemDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table( name = "item" )
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;
    private String       description;
    private String       sku;
    private BigDecimal   price;
    @OneToOne
    @JoinColumn( name = "item_category_id" )
    private ItemCategory itemCategory;
    @OneToOne
    @JoinColumn( name = "item_type_id" )
    private ItemType     itemType;

    public Item( ItemDto itemDto,
                 ItemCategory itemCategory,
                 ItemType itemType ) {
        this.id           = itemDto.getId( );
        this.description  = itemDto.getDescription( );
        this.sku          = itemDto.getSku( );
        this.price        = itemDto.getPrice( );
        this.itemCategory = itemCategory;
        this.itemType     = itemType;
    }

    public Item( ItemDto itemDto ) {
        this.id          = itemDto.getId( );
        this.description = itemDto.getDescription( );
        this.sku         = itemDto.getSku( );
        this.price       = itemDto.getPrice( );
    }

    public ItemDto toItem( ) {
        return ItemDto.builder( )
                .id( this.id )
                .description( this.description )
                .sku( this.sku )
                .price( this.price )
                .category( this.itemCategory.getId() )
                .itemType( this.itemType.getId() )
                .build( );
    }

}
