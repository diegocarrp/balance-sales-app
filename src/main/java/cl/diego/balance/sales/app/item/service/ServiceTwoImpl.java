package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(value = "balance-sales.config.service", havingValue = "TWO")
public class ServiceTwoImpl implements ItemService{

    @Override
    public void saveItem( ItemDto item ) throws BadInputException {

    }

    @Override
    public ItemDto getItemBySku( String sku ) throws ItemNotFoundException {
        return null;
    }

    @Override
    public void updateItem( ItemDto Item ) throws BadInputException {

    }

    @Override
    public void deleteItem( Long id ) {

    }
}
