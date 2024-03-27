package cl.diego.balance.sales.app.sale.service.impl;

import cl.diego.balance.sales.app.sale.exception.PaymentMethodNotFoundException;
import cl.diego.balance.sales.app.sale.repository.PaymentMethodRepository;
import cl.diego.balance.sales.app.sale.repository.model.PaymentMethod;
import cl.diego.balance.sales.app.sale.service.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod findById( Long id ) {
        return paymentMethodRepository.findById( id )
                .orElseThrow( PaymentMethodNotFoundException::new );
    }
}
