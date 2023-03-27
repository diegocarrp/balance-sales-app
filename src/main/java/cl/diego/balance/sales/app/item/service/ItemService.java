package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.exception.ItemNotFoundException;

public interface ItemService {

    void saveItem( ItemDto item ) throws BadInputException;

    ItemDto getItemBySku( String sku ) throws ItemNotFoundException;

    void updateItem( ItemDto Item ) throws BadInputException;

    void deleteItem( String id );

}
