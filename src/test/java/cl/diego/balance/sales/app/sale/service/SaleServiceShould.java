package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.config.ApplicationConfig;
import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.exception.ItemNotFoundException;
import cl.diego.balance.sales.app.item.service.ItemCategoryService;
import cl.diego.balance.sales.app.item.service.ItemService;
import cl.diego.balance.sales.app.sale.client.CustomerClient;
import cl.diego.balance.sales.app.sale.client.customer.exception.CustomerNotFoundException;
import cl.diego.balance.sales.app.sale.client.dto.CustomerDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;
import cl.diego.balance.sales.app.sale.exception.IncompletePaymentException;
import cl.diego.balance.sales.app.sale.repository.SaleRepository;
import cl.diego.balance.sales.app.sale.repository.model.PaymentMethod;
import cl.diego.balance.sales.app.sale.repository.model.Sale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static cl.diego.balance.commons.testing.UtilForTesting.getMappedObjectFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith( SpringExtension.class )
@ContextConfiguration( classes = SaleServiceShould.TestConfiguration.class )
class SaleServiceShould {

    static class TestConfiguration {
        @Bean
        public SaleService saleService( final SaleRepository saleRepository,
                                        final PaymentMethodService paymentMethodService,
                                        final ItemCategoryService itemCategoryService,
                                        final SaleItemService saleItemService,
                                        final CustomerClient customerClient,
                                        final ItemService itemService,
                                        final ApplicationConfig applicationConfig ) {
            return new SaleServiceImpl( saleRepository,
                    paymentMethodService,
                    itemCategoryService,
                    saleItemService,
                    customerClient,
                    itemService,
                    applicationConfig );
        }
    }


    @Autowired
    private SaleServiceImpl      saleService;
    @MockBean
    private SaleRepository       saleRepository;
    @MockBean
    private PaymentMethodService paymentMethodService;
    @MockBean
    private ItemCategoryService  itemCategoryService;
    @MockBean
    private SaleItemService      saleItemService;
    @MockBean
    private CustomerClient       customerClient;
    @MockBean
    private ItemService          itemService;
    @MockBean
    private ApplicationConfig    applicationConfig;

    private static SaleRequest   saleRequest;
    private static SaleDto       saleOneDto;
    private static Sale          saleOne;
    private static CustomerDto   cashier;
    private static CustomerDto   customer;
    private static PaymentMethod paymentMethod;
    private static ItemDto       itemOneDto;
    private static ItemDto       itemTwoDto;

    private static final String SALE_DTO_ONE   = "sale/saleDtoOne.json";
    private static final String SALE_ONE       = "sale/saleOne.json";
    private static final String CASHIER        = "sale/cashier.json";
    private static final String CUSTOMER       = "sale/customer.json";
    private static final String PAYMENT_METHOD = "sale/paymentMethod.json";
    private static final String ITEM_ONE       = "sale/itemOne.json";
    private static final String ITEM_TWO       = "sale/itemTwo.json";

    @BeforeAll
    static void setUp( ) throws IOException {
        saleRequest   = getMappedObjectFromFile( SALE_DTO_ONE, SaleRequest.class );
        saleOneDto    = getMappedObjectFromFile( SALE_DTO_ONE, SaleDto.class );
        saleOne       = getMappedObjectFromFile( SALE_ONE, Sale.class );
        cashier       = getMappedObjectFromFile( CASHIER, CustomerDto.class );
        customer      = getMappedObjectFromFile( CUSTOMER, CustomerDto.class );
        paymentMethod = getMappedObjectFromFile( PAYMENT_METHOD, PaymentMethod.class );
        itemOneDto    = getMappedObjectFromFile( ITEM_ONE, ItemDto.class );
        itemTwoDto    = getMappedObjectFromFile( ITEM_TWO, ItemDto.class );
    }

    @Test
    void registerSaleSuccessfully( ) {
        when( customerClient.findById( 321L ) ).thenReturn( cashier );
        when( customerClient.findById( 123L ) ).thenReturn( customer );
        when( paymentMethodService.findById( 1L ) ).thenReturn( paymentMethod );
        when( itemService.getItemBySku( "111" ) ).thenReturn( itemOneDto );
        when( itemService.getItemBySku( "222" ) ).thenReturn( itemTwoDto );
        when( saleRepository.save( any( ) ) ).thenReturn( saleOne );

        SaleDto saleDto = saleService.registerSale( saleRequest );

        assertEquals( BigDecimal.valueOf( 2500 ), saleDto.getTotalAmount( ) );
        assertEquals( 321L, saleDto.getCashierId( ) );
        assertEquals( 123L, saleDto.getCustomerId( ) );
        assertEquals( saleOneDto.getDatetime( ), saleDto.getDatetime( ) );

        verify( saleRepository ).save( any( ) );
        verify( paymentMethodService ).findById( anyLong( ) );
        verify( itemService, times( 2 ) ).getItemBySku( anyString( ) );
        verify( customerClient, times( 2 ) ).findById( anyLong( ) );
    }

    @Test
    void failWithInvalidCustomer( ) {
        when( customerClient.findById( 123L ) ).thenThrow( new CustomerNotFoundException( "123", "ID" ) );

        assertThrows( CustomerNotFoundException.class, ( ) -> {
            saleService.registerSale( saleRequest );
        } );
    }

    @Test
    void failWithInvalidCashier( ) {
        when( customerClient.findById( 321L ) ).thenThrow( new CustomerNotFoundException( "321", "ID" ) );

        assertThrows( CustomerNotFoundException.class, ( ) -> {
            saleService.registerSale( saleRequest );
        } );
    }

    @Test
    void failWithUnexistantItem( ) {
        when( customerClient.findById( 321L ) ).thenReturn( cashier );
        when( customerClient.findById( 123L ) ).thenReturn( customer );
        when( paymentMethodService.findById( 1L ) ).thenReturn( paymentMethod );
        when( itemService.getItemBySku( "111" ) ).thenReturn( itemOneDto );
        when( itemService.getItemBySku( "222" ) ).thenThrow( new ItemNotFoundException( ) );

        assertThrows( ItemNotFoundException.class, ( ) -> {
            saleService.registerSale( saleRequest );
        } );
    }

    @Test
    void failWithIncompletePayment( ) {
        when( customerClient.findById( 321L ) ).thenReturn( cashier );
        when( customerClient.findById( 123L ) ).thenReturn( customer );
        when( paymentMethodService.findById( 1L ) ).thenReturn( paymentMethod );
        when( itemService.getItemBySku( "111" ) ).thenReturn( itemOneDto );
        when( itemService.getItemBySku( "222" ) ).thenReturn( itemTwoDto );
        when( saleRepository.save( any( ) ) ).thenReturn( saleOne );

        saleOneDto.getPayments( ).get( 0 ).setAmount( new BigDecimal( 500 ) );

        assertThrows( IncompletePaymentException.class, ( ) -> {
            saleService.registerSale( saleRequest );
        } );
    }

    @Test
    void returnSaleDetailsForCategories( ) {

    }
}