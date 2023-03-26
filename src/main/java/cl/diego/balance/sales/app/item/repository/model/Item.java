package cl.diego.balance.sales.app.item.repository.model;

import cl.diego.balance.sales.app.item.dto.ItemDto;
import lombok.*;

import javax.persistence.Entity;
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
    private String       id;
    private String       description;
    private String       sku;
    private BigDecimal   price;
    @OneToOne
    @JoinColumn( name = "item_category_id" )
    private ItemCategory itemCategory;
    @OneToOne
    @JoinColumn( name = "item_type_id" )
    private ItemType     itemType;

    public Item( ItemDto itemDto, ItemCategory itemCategory, ItemType itemType ) {
        this.id           = itemDto.getId( );
        this.description  = itemDto.getDescription( );
        this.sku          = itemDto.getSku( );
        this.price        = itemDto.getPrice( );
        this.itemCategory = itemCategory;
        this.itemType     = itemType;
    }

    public Item( ItemDto itemDto ) {
        this.id           = itemDto.getId( );
        this.description  = itemDto.getDescription( );
        this.sku          = itemDto.getSku( );
        this.price        = itemDto.getPrice( );
    }

    public ItemDto toItem() {
        return ItemDto.builder()
                .id( this.id )
                .description( this.description )
                .sku( this.sku )
                .price( this.price )
                .categoryId( this.itemCategory.getId() )
                .itemType( this.itemType.getId() )
                .build();
    }

}
