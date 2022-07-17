package cl.diego.balance.sales.app.item.repository;

import cl.diego.balance.sales.app.item.repository.domain.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {

    Optional<ItemType> findByDescription( String description );
}