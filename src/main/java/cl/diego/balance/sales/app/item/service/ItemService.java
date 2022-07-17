package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.sales.app.item.dto.ItemDto;

public interface ItemService {

    void saveItem( ItemDto item ); //throws BadInputException;

    ItemDto getItemBySku( String sku );// throws ItemNotFoundException;

    void updateItem( ItemDto Item ); //throws BadInputException;

    void deleteItem( Long id );

}
