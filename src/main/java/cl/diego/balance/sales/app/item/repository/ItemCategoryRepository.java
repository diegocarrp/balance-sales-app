package cl.diego.balance.sales.app.item.repository;

import cl.diego.balance.sales.app.item.repository.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, String> {
    Optional<ItemCategory> findByDescription( String description );
}
