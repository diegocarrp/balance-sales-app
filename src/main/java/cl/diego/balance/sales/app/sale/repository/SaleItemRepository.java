package cl.diego.balance.sales.app.sale.repository;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import cl.diego.balance.sales.app.sale.repository.domain.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    @Query(value = "select si from SaleItem si " +
            "join Sale s on si.sale  = s " +
            "join Item i on si.sku = i.sku " +
            "where s.datetime between :startDate and :endDate " +
            "and i.itemCategoryId = :itemCategory")
    List<SaleItem> findAllByCategoryBetweenDates( LocalDateTime startDate, LocalDateTime endDate, ItemCategory itemCategory );

}
