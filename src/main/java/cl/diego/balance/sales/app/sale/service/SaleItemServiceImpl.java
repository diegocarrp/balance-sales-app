package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.repository.domain.Item;
import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import cl.diego.balance.sales.app.item.service.ItemService;
import cl.diego.balance.sales.app.sale.repository.SaleItemRepository;
import cl.diego.balance.sales.app.sale.repository.domain.SaleItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;
    private final ItemService        itemService;

    @Override
    public BigDecimal totalSaleByCategory( ItemCategory category ) {
        List<SaleItem> saleItems = saleItemRepository.findAll( );

        return saleItems.stream( ).filter( saleItem -> {
                    ItemDto itemFound = itemService.getItemBySku( saleItem.getSku( ) );
                    return itemFound.getCategoryId( ).equals( category.getId() );
                } ).map( SaleItem::getTotal )
                .reduce( BigDecimal.ZERO, BigDecimal::add );

    }
}
