package cl.diego.balance.sales.app.item.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends ApiException {
    public ItemNotFoundException( ) {
        super( HttpStatus.NOT_FOUND, "Item wasn't found." );
    }
}
