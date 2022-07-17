package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.sales.app.item.dto.CategoryDto;

public interface CategoryService {

    void saveCategory( CategoryDto category ); //throws BadInputException;

    CategoryDto getCategoryByDescription( String description );// throws ItemNotFoundException;

    CategoryDto getCategoryById( Long id );// throws ItemNotFoundException;

}
