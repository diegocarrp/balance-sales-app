package cl.diego.balance.sales.app.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ItemDto {

    private Long     id;
    @NotNull
    private String     description;
    @NotEmpty
    private String     sku;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long     category;
    @NotNull
    private Long     itemType;

    public void updateItem( ItemDto item ) {
        this.description = item.getDescription( );
        this.sku         = item.getSku( );
        this.price       = item.getPrice( );
        this.category    = item.getCategory( );
        this.itemType    = item.getItemType( );
    }
}
