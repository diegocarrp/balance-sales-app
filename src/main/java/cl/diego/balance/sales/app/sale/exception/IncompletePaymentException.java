package cl.diego.balance.sales.app.sale.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class IncompletePaymentException extends ApiException {

    public IncompletePaymentException( ) {
        super( HttpStatus.CONFLICT, "Payments must sum the total of the purchase." );
    }

}
