package cl.diego.balance.sales.app.item.repository.domain;

import cl.diego.balance.sales.app.item.dto.ItemDto;
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
    private Long         id;
    private String       description;
    private String       sku;
    private BigDecimal   price;
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "itemCategoryId", referencedColumnName = "id")
    private ItemCategory itemCategoryId;
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "itemTypeId", referencedColumnName = "id")
    private ItemType     itemTypeId;

    public Item( ItemDto itemDto ) {
        if(itemDto.getId() != null) {
            this.id = itemDto.getId();
        }
        this.description = itemDto.getDescription( );
        this.sku         = itemDto.getSku( );
        this.price       = itemDto.getPrice( );
//        this.category = itemDto.getCategoryId();
//        this.itemType = itemDto.getItemType();
    }

    public Item( ItemDto item,
                 ItemCategory category,
                 ItemType type ) {
        this.description    = item.getDescription( );
        this.sku            = item.getSku( );
        this.price          = item.getPrice( );
        this.itemCategoryId = category;
        this.itemTypeId     = type;
    }

    public ItemDto toItem( ) {
        return ItemDto.builder( )
                .id( this.id )
                .description( this.description )
                .sku( this.sku )
                .price( this.price )
                .categoryId( this.itemCategoryId.getId( ) )
                .itemType( this.itemTypeId.getId( ) )
                .build( );
    }

}
