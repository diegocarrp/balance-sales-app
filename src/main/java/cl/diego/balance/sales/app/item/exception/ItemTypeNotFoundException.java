package cl.diego.balance.sales.app.item.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ItemTypeNotFoundException extends ApiException {
    public ItemTypeNotFoundException( ) {
        super( HttpStatus.NOT_FOUND, "Item Type wasn't found." );
    }
}
