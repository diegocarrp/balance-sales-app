package cl.diego.balance.sales.app.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ItemDto {

    private String     id;
    @NotNull
    private String     description;
    @NotBlank
    private String     sku;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String     category;
    @NotNull
    private String     itemType;

    public void updateItem( ItemDto item ) {
        this.description = item.getDescription( );
        this.sku         = item.getSku( );
        this.price       = item.getPrice( );
        this.category    = item.getCategory( );
        this.itemType    = item.getItemType( );
    }
}
