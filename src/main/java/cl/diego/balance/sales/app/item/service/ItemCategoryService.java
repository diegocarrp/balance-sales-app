package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.sales.app.item.dto.CategoryDto;
import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;

import java.util.List;

public interface ItemCategoryService {

    void saveCategory( CategoryDto category ); //throws BadInputException;

    CategoryDto getCategoryByDescription( String description );// throws ItemNotFoundException;

    CategoryDto getCategoryById( Long id );// throws ItemNotFoundException;

    ItemCategory findById( Long id );

    List<ItemCategory> findAll( );
}
