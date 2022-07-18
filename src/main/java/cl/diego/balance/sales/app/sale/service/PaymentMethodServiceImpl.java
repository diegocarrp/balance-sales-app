package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.repository.PaymentMethodRepository;
import cl.diego.balance.sales.app.sale.repository.domain.PaymentMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod findById( Long id ) {
        return paymentMethodRepository.findById( id )
                .orElseThrow();
    }
}
