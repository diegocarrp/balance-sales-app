package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;

import java.util.List;

public interface ItemCategoryService {

    ItemCategory findById( Long id );
    List<ItemCategory> findAll();

}
