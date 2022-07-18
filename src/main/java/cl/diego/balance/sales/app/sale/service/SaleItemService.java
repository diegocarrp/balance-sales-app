package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;

import java.math.BigDecimal;

public interface SaleItemService {

    BigDecimal totalSaleByCategory( ItemCategory category );

}
