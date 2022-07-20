package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.sales.app.item.dto.ItemTypeDto;
import cl.diego.balance.sales.app.item.exception.ItemTypeNotFoundException;
import cl.diego.balance.sales.app.item.repository.ItemTypeRepository;
import cl.diego.balance.sales.app.item.repository.domain.ItemType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;
    private final Validator          validator;

    public ItemTypeServiceImpl( ItemTypeRepository itemTypeRepository ) {
        this.itemTypeRepository = itemTypeRepository;
        this.validator          = Validation.byDefaultProvider( )
                .configure( )
                .messageInterpolator( new ParameterMessageInterpolator( ) )
                .buildValidatorFactory( )
                .getValidator( );

    }

    @Override
    public void saveItemType( ItemTypeDto itemType ) {
        validateItemType( itemType );
        itemTypeRepository.save( new ItemType( itemType ) );
    }

    @Override
    public ItemTypeDto getItemTypeByDescription( String description ) {
        ItemType itemTypeDb = itemTypeRepository.findByDescription( description )
                .orElseThrow( ItemTypeNotFoundException::new );
        return itemTypeDb.toItemType( );
    }

    @Override
    public ItemTypeDto getItemTypeById( Long id ) {
        ItemType itemTypeDb = itemTypeRepository.findById( id )
                .orElseThrow( ItemTypeNotFoundException::new );
        return itemTypeDb.toItemType( );
    }

    @Override
    public ItemType findById( Long id ) {
        return itemTypeRepository.findById( id )
                .orElseThrow( ItemTypeNotFoundException::new );
    }

    private void validateItemType( ItemTypeDto itemType ) {
        Set<ConstraintViolation<ItemTypeDto>> violations = validator.validate( itemType );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        if( !descriptions.isEmpty( ) ) {
            throw new ApiValidationException( "ItemType wasn't created because of: ", descriptions );
        }
    }
}
