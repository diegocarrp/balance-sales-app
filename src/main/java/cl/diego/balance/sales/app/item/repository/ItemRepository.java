package cl.diego.balance.sales.app.item.repository;

import cl.diego.balance.sales.app.item.repository.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    @Query("{sku: '?0'}")
    Optional<Item> findBySku(String sku);

}
