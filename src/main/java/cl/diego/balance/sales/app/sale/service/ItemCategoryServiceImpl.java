package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.ItemCategoryRepository;
import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    @Override
    public ItemCategory findById( Long id ) {
        return itemCategoryRepository.findById( id )
                .orElseThrow();
    }

    @Override
    public List<ItemCategory> findAll( ) {
        return itemCategoryRepository.findAll();
    }
}
