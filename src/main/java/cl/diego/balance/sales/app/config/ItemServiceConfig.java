package cl.diego.balance.sales.app.config;

import cl.diego.balance.sales.app.item.repository.ItemRepository;
import cl.diego.balance.sales.app.item.service.*;
import cl.diego.balance.sales.app.item.service.impl.ItemServiceImpl;
import cl.diego.balance.sales.app.item.service.impl.ServiceTwoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@Slf4j
public class ItemServiceConfig {

    @Bean
    @ConditionalOnProperty( value = "balance-sales.config.service", havingValue = "ONE", matchIfMissing = true )
    public ItemService itemService( ItemRepository itemRepository,
                                    ItemCategoryService itemCategoryService,
                                    ItemTypeService itemTypeService ) {
        log.info( "returning ItemServiceImpl" );
        return new ItemServiceImpl( itemRepository, itemCategoryService, itemTypeService );
    }

    @Bean
    @ConditionalOnProperty( value = "balance-sales.config.service", havingValue = "TWO" )
    public ItemService itemService( ) {
        log.info( "returning ServiceTwoImpl" );
        return new ServiceTwoImpl( );
    }

}
