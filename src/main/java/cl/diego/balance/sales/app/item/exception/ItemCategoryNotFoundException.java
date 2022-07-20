package cl.diego.balance.sales.app.item.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ItemCategoryNotFoundException extends ApiException {
    public ItemCategoryNotFoundException( ) {
        super( HttpStatus.NOT_FOUND, "Category wasn't found." );
    }
}
