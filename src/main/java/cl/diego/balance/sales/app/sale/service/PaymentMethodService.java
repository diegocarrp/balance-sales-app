package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.repository.domain.PaymentMethod;

public interface PaymentMethodService {

    PaymentMethod findById( Long id );

}
