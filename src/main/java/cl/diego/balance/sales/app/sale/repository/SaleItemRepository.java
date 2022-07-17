package cl.diego.balance.sales.app.sale.repository;

import cl.diego.balance.sales.app.sale.repository.domain.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
