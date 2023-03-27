package cl.diego.balance.sales.app.sale.repository;

import cl.diego.balance.sales.app.sale.repository.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
