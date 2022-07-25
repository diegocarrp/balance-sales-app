package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.sales.app.item.dto.CategoryDto;
import cl.diego.balance.sales.app.item.exception.ItemCategoryNotFoundException;
import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;

import java.util.List;

public interface ItemCategoryService {

    void saveCategory( CategoryDto category ) throws BadInputException;

    CategoryDto getCategoryByDescription( String description ) throws ItemCategoryNotFoundException;

    CategoryDto getCategoryById( Long id ) throws ItemCategoryNotFoundException;

    ItemCategory findById( Long id ) throws ItemCategoryNotFoundException;

    List<ItemCategory> findAll( );
}
