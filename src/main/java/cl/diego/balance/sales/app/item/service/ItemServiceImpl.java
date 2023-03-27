package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.exception.ItemNotFoundException;
import cl.diego.balance.sales.app.item.repository.ItemRepository;
import cl.diego.balance.sales.app.item.repository.model.Item;
import cl.diego.balance.sales.app.item.repository.model.ItemCategory;
import cl.diego.balance.sales.app.item.repository.model.ItemType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@ConditionalOnProperty(value = "balance-sales.config.service", havingValue = "ONE", matchIfMissing = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository      itemRepository;
    private final ItemCategoryService itemCategoryService;
    private final ItemTypeService     itemTypeService;
    private final Validator           validator;

    public ItemServiceImpl( ItemRepository itemRepository,
                            ItemCategoryService itemCategoryService,
                            ItemTypeService itemTypeService ) {
        this.itemRepository      = itemRepository;
        this.itemCategoryService = itemCategoryService;
        this.itemTypeService     = itemTypeService;
        this.validator           = Validation.byDefaultProvider( )
                .configure( )
                .messageInterpolator( new ParameterMessageInterpolator( ) )
                .buildValidatorFactory( )
                .getValidator( );
    }

    @Override
    public void saveItem( ItemDto item ) throws BadInputException {
        validateItem( item );
        ItemCategory category = itemCategoryService.findById( item.getCategoryId( ) );
        ItemType type = itemTypeService.findById( item.getItemType( ) );
        itemRepository.save( new Item( item, category, type ) );
    }

    @Override
    public ItemDto getItemBySku( String sku ) throws ItemNotFoundException {
        Item itemDb = itemRepository.findBySku( sku )
                .orElseThrow( ItemNotFoundException::new );
        log.info( "itemFound: <{}>", itemDb );
        return itemDb.toItem( );
    }

    @Override
    public void updateItem( ItemDto item ) throws BadInputException {
        ItemDto itemDb = getItemBySku( item.getSku( ) );
        itemDb.updateItem( item );
        itemRepository.save( new Item( itemDb ) );
    }

    @Override
    public void deleteItem( String id ) {
        itemRepository.deleteById( id );
    }

    private void validateItem( ItemDto item ) {
        Set<ConstraintViolation<ItemDto>> violations = validator.validate( item );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        Optional<Item> dbItem = itemRepository.findBySku( item.getSku() );
        if( dbItem.isPresent( ) ) {
            descriptions.add( "sku - sku already exists" );
        }

        if( !descriptions.isEmpty( ) ) {
            throw new ApiValidationException( "Item wasn't created because of: ", descriptions );
        }
    }
}
