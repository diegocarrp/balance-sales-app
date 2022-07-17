package cl.diego.balance.sales.app.sale.repository;

import cl.diego.balance.sales.app.sale.repository.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
