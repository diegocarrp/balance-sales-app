package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.sales.app.item.dto.ItemTypeDto;
import cl.diego.balance.sales.app.item.repository.model.ItemType;

public interface ItemTypeService {

    void saveItemType( ItemTypeDto itemType ); //throws BadInputException;

    ItemTypeDto getItemTypeByDescription( String description );// throws ItemNotFoundException;

    ItemTypeDto getItemTypeById( Long id );// throws ItemNotFoundException;

    ItemType findById( Long id );

}
