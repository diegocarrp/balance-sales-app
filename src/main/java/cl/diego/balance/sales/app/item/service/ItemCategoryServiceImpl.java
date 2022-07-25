package cl.diego.balance.sales.app.item.service;

import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.sales.app.item.dto.CategoryDto;
import cl.diego.balance.sales.app.item.exception.ItemCategoryNotFoundException;
import cl.diego.balance.sales.app.item.repository.ItemCategoryRepository;
import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;
    private final Validator              validator;

    public ItemCategoryServiceImpl( ItemCategoryRepository itemCategoryRepository ) {
        this.itemCategoryRepository = itemCategoryRepository;
        this.validator              = Validation.byDefaultProvider( )
                .configure( )
                .messageInterpolator( new ParameterMessageInterpolator( ) )
                .buildValidatorFactory( )
                .getValidator( );
    }

    @Override
    public void saveCategory( CategoryDto category ) {
        validateCategory( category );
        itemCategoryRepository.save( new ItemCategory( category ) );
    }

    @Override
    public CategoryDto getCategoryByDescription( String description ) {
        ItemCategory categoryDb = itemCategoryRepository.findByDescription( description.toUpperCase( Locale.ROOT ) )
                .orElseThrow( ItemCategoryNotFoundException::new );
        return categoryDb.toItemType( );
    }

    @Override
    public CategoryDto getCategoryById( Long id ) {
        ItemCategory categoryDb = itemCategoryRepository.findById( id )
                .orElseThrow( ItemCategoryNotFoundException::new );
        return categoryDb.toItemType( );
    }

    @Override
    public ItemCategory findById( Long id ) {
        return itemCategoryRepository.findById( id )
                .orElseThrow( ItemCategoryNotFoundException::new );
    }

    @Override
    public List<ItemCategory> findAll( ) {
        return itemCategoryRepository.findAll( );
    }

    private void validateCategory( CategoryDto category ) {
        Set<ConstraintViolation<CategoryDto>> violations = validator.validate( category );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        if( !descriptions.isEmpty( ) ) {
            throw new ApiValidationException( "Item Category wasn't created because of: ", descriptions );
        }
        category.setDescription( category.getDescription().toUpperCase() );
    }
}
