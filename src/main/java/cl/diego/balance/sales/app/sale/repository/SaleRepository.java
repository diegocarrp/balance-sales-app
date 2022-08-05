package cl.diego.balance.sales.app.sale.repository;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import cl.diego.balance.sales.app.sale.repository.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByDatetimeBetween( LocalDateTime startDate,
                                         LocalDateTime endDate );
}
