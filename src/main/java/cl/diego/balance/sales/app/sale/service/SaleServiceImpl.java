package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.model.ItemCategory;
import cl.diego.balance.sales.app.item.service.ItemCategoryService;
import cl.diego.balance.sales.app.item.service.ItemService;
import cl.diego.balance.sales.app.sale.client.CustomerClient;
import cl.diego.balance.sales.app.sale.client.dto.CustomerDto;
import cl.diego.balance.sales.app.config.ApplicationConfig;
import cl.diego.balance.sales.app.sale.dto.*;
import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;
import cl.diego.balance.sales.app.sale.exception.IncompletePaymentException;
import cl.diego.balance.sales.app.sale.repository.SaleRepository;
import cl.diego.balance.sales.app.sale.repository.model.Payment;
import cl.diego.balance.sales.app.sale.repository.model.PaymentMethod;
import cl.diego.balance.sales.app.sale.repository.model.Sale;
import cl.diego.balance.sales.app.sale.repository.model.SaleItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository       saleRepository;
    private final PaymentMethodService paymentMethodService;
    private final ItemCategoryService  itemCategoryService;
    private final SaleItemService      saleItemService;
    private final CustomerClient       customerClient;
    private final ItemService          itemService;
    private final ApplicationConfig    applicationConfig;

    public SaleServiceImpl( SaleRepository saleRepository,
                            PaymentMethodService paymentMethodService,
                            ItemCategoryService itemCategoryService,
                            SaleItemService saleItemService,
                            CustomerClient customerClient,
                            ItemService itemService,
                            ApplicationConfig applicationConfig ) {
        this.saleRepository       = saleRepository;
        this.paymentMethodService = paymentMethodService;
        this.itemCategoryService  = itemCategoryService;
        this.saleItemService      = saleItemService;
        this.customerClient       = customerClient;
        this.itemService          = itemService;
        this.applicationConfig    = applicationConfig;
        log.info( "ApplicationConfig:" + applicationConfig );
    }

    @Override
    public SaleDto registerSale( SaleRequest request ) {

        SaleDto sale = new SaleDto( request );

        Sale saleDb = buildSale( sale );

        List<Payment> payments = buildPayments( sale, saleDb );
        saleDb.setPayments( payments );

        List<SaleItem> items = buildSaleItems( sale, saleDb );
        saleDb.setItems( items );

        Sale savedSale = saleRepository.save( saleDb );
        return savedSale.toSale( );
    }

    private Sale buildSale( SaleDto sale ) {

        CustomerDto customer = customerClient.findById( sale.getCustomerId( ) );
        CustomerDto cashier = customerClient.findById( sale.getCashierId( ) );

        return Sale.builder( )
                .customerId( customer.getId( ) )
                .cashierId( cashier.getId( ) )
                .datetime( sale.getDatetime( ) )
                .totalAmount( sale.getTotalAmount( ) )
                .build( );
    }

    private List<SaleItem> buildSaleItems( SaleDto sale,
                                           Sale saleDb ) {

        return sale.getItems( ).stream( )
                .map( saleItemDto -> {
                    itemService.getItemBySku( saleItemDto.getSku( ) );

                    return SaleItem.builder( )
                            .sku( saleItemDto.getSku( ) )
                            .total( saleItemDto.getTotal( ) )
                            .quantity( saleItemDto.getQuantity( ) )
                            .sale( saleDb )
                            .build( );
                } )
                .collect( Collectors.toList( ) );
    }

    private List<Payment> buildPayments( SaleDto sale,
                                         Sale saleDb ) {
        validateAmount( sale );

        return sale.getPayments( ).stream( )
                .map( p -> {
                    PaymentMethod paymentMethod = paymentMethodService.findById( p.getId( ) );
                    return Payment.builder( )
                            .amount( p.getAmount( ) )
                            .paymentMethod( paymentMethod )
                            .sale( saleDb )
                            .build( );
                } ).collect( Collectors.toList( ) );
    }

    private void validateAmount( SaleDto saleDto ) {
        Optional<BigDecimal> paymentsTotalAmount = saleDto.getPayments( ).stream( )
                .map( PaymentDto::getAmount )
                .reduce( BigDecimal::add );

        paymentsTotalAmount.ifPresent( amount -> {
            if( amount.compareTo( saleDto.getTotalAmount( ) ) != 0 ) throw new IncompletePaymentException( );
        } );
    }

    @Override
    public SaleResponseDto getSaleById( Long id ) {
        Sale saleDb = saleRepository.findById( id )
                .orElseThrow( );

        CustomerDto customer = customerClient.findById( saleDb.getCustomerId( ) );
        CustomerDto cashier = customerClient.findById( saleDb.getCashierId( ) );

        SaleDto saleDto = saleDb.toSale( );

        return SaleResponseDto.builder( )
                .id( saleDto.getId( ) )
                .customer( customer )
                .cashier( cashier )
                .items( saleDto.getItems( ) )
                .payments( saleDto.getPayments( ) )
                .datetime( saleDto.getDatetime( ) )
                .totalAmount( saleDto.getTotalAmount( ) )
                .build( );
    }

    @Override
    public SaleDetailDto getSaleDetailByCategory( LocalDateTime startDate,
                                                  LocalDateTime endDate ) {
        List<ItemCategory> categories = itemCategoryService.findAll( );

        if( endDate == null ) {
            endDate = startDate.plusMonths( 1 );
        }

        LocalDateTime finalEndDate = endDate;
        List<SaleDetailItemDto> detailItems = categories.stream( ).map( cat -> {
            BigDecimal categoryTotal = saleItemService.totalSaleByCategory( startDate, finalEndDate, cat );
            return SaleDetailItemDto.builder( )
                    .category( cat.getDescription( ) )
                    .salesAmount( categoryTotal )
                    .build( );
        } ).collect( Collectors.toList( ) );

        return SaleDetailDto.builder( )
                .details( detailItems )
                .endDate( endDate )
                .startDate( startDate )
                .build( );
    }
}
