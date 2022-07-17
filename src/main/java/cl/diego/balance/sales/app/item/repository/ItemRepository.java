package cl.diego.balance.sales.app.item.repository;

import cl.diego.balance.sales.app.item.repository.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findBySku( String sku);

}
