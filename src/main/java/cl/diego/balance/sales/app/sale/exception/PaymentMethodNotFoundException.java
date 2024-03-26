package cl.diego.balance.sales.app.sale.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PaymentMethodNotFoundException extends ApiException {
    public PaymentMethodNotFoundException( ) {
        super( HttpStatus.NOT_FOUND, "Payment Method wasn't found." );
    }
}
