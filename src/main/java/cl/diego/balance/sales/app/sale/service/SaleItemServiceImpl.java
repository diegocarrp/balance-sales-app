package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.model.ItemCategory;
import cl.diego.balance.sales.app.sale.repository.SaleItemRepository;
import cl.diego.balance.sales.app.sale.repository.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;

    @Override
    public BigDecimal totalSaleByCategory( LocalDateTime startDate,
                                           LocalDateTime endDate,
                                           ItemCategory category ) {
        List<SaleItem> saleItems = saleItemRepository.findAllByCategoryBetweenDates( startDate, endDate, category );

        return saleItems.stream( ).map( SaleItem::getTotal )
                .reduce( BigDecimal.ZERO, BigDecimal::add );

    }
}
