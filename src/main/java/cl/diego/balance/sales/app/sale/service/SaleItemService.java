package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SaleItemService {

    BigDecimal totalSaleByCategory( LocalDateTime startDate,
                                    LocalDateTime endDate,
                                    ItemCategory category );

}
