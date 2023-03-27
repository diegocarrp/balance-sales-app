package cl.diego.balance.sales.app.item.repository;

import cl.diego.balance.sales.app.item.repository.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findBySku(String sku);

}
