package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import cl.diego.balance.sales.app.item.service.ItemCategoryService;
import cl.diego.balance.sales.app.item.service.ItemService;
import cl.diego.balance.sales.app.sale.client.CustomerClient;
import cl.diego.balance.sales.app.sale.client.dto.CustomerDto;
import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDetailItemDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SaleResponseDto;
import cl.diego.balance.sales.app.sale.repository.SaleRepository;
import cl.diego.balance.sales.app.sale.repository.domain.Payment;
import cl.diego.balance.sales.app.sale.repository.domain.PaymentMethod;
import cl.diego.balance.sales.app.sale.repository.domain.Sale;
import cl.diego.balance.sales.app.sale.repository.domain.SaleItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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

    public SaleServiceImpl( SaleRepository saleRepository,
                            PaymentMethodService paymentMethodService,
                            ItemCategoryService itemCategoryService,
                            SaleItemService saleItemService,
                            CustomerClient customerClient,
                            ItemService itemService ) {
        this.saleRepository       = saleRepository;
        this.paymentMethodService = paymentMethodService;
        this.itemCategoryService  = itemCategoryService;
        this.saleItemService      = saleItemService;
        this.customerClient       = customerClient;
        this.itemService          = itemService;
    }

    @Override
    public void registerSale( SaleDto sale ) {

        Sale saleDb = buildSale( sale );

        List<Payment> payments = buildPayments( sale, saleDb );
        saleDb.setPayments( payments );

        List<SaleItem> items = buildSaleItems( sale, saleDb );
        saleDb.setItems( items );

        saleRepository.save( saleDb );
    }

    private Sale buildSale( SaleDto sale ) {

        CustomerDto customer = customerClient.findById( sale.getCustomerId( ) );
        CustomerDto cashier = customerClient.findById( sale.getCashierId( ) );

        return Sale.builder( )
                .customerId( sale.getCustomerId( ) )
                .cashierId( sale.getCashierId( ) )
                .datetime( sale.getDatetime( ) )
                .totalAmount( sale.getTotalAmount( ) )
                .build( );
    }

    private List<SaleItem> buildSaleItems( SaleDto sale,
                                           Sale saleDb ) {

        return sale.getItems( ).stream( )
                .map( saleItemDto -> {
                    itemService.getItemBySku( saleItemDto.getSku() );

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
        return sale.getPayments( ).stream( )
                .map( p -> {
                    PaymentMethod paymentMethod = paymentMethodService.findById( p.getPaymentMethodId( ) );
                    return Payment.builder( )
                            .amount( p.getAmount( ) )
                            .paymentMethod( paymentMethod )
                            .sale( saleDb )
                            .build( );
                } ).collect( Collectors.toList( ) );
    }

    @Override
    public SaleResponseDto getSaleById( Long id ) {
        Sale saleDb = saleRepository.findById( id )
                .orElseThrow( );

        CustomerDto customer = customerClient.findById( saleDb.getCustomerId( ) );
        CustomerDto cashier = customerClient.findById( saleDb.getCashierId( ) );

        SaleDto saleDto = saleDb.toSale();

        return SaleResponseDto.builder()
                .id( saleDto.getId() )
                .customer( customer )
                .cashier( cashier )
                .items( saleDto.getItems() )
                .payments( saleDto.getPayments() )
                .datetime( saleDto.getDatetime() )
                .totalAmount( saleDto.getTotalAmount() )
                .build();
    }

    @Override
    public SaleDetailDto getSaleDetailByCategory( ) {
        List<ItemCategory> categories = itemCategoryService.findAll( );

        List<SaleDetailItemDto> detailItems = categories.stream( ).map( cat -> {
            BigDecimal categoryTotal = saleItemService.totalSaleByCategory( cat );
            return SaleDetailItemDto.builder( )
                    .category( cat.getDescription( ) )
                    .salesAmount( categoryTotal )
                    .build( );
        } ).collect( Collectors.toList( ) );

        return SaleDetailDto.builder( )
                .details( detailItems )
                .build( );
    }
}
