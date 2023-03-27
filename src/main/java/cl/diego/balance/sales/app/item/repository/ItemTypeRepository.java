package cl.diego.balance.sales.app.item.repository;

import cl.diego.balance.sales.app.item.repository.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemType, String> {

    Optional<ItemType> findByDescription( String description );
}
