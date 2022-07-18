package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDetailItemDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
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

    public SaleServiceImpl( SaleRepository saleRepository,
                            PaymentMethodService paymentMethodService,
                            ItemCategoryService itemCategoryService,
                            SaleItemService saleItemService ) {
        this.saleRepository       = saleRepository;
        this.paymentMethodService = paymentMethodService;
        this.itemCategoryService  = itemCategoryService;
        this.saleItemService      = saleItemService;
    }

    @Override
    public void registerSale( SaleDto sale ) {

        Sale saleDb = Sale.builder( )
                .customerId( sale.getCustomerId( ) )
                .cashierId( sale.getCashierId( ) )
                .datetime( sale.getDatetime( ) )
                .totalAmount( sale.getTotalAmount( ) )
                .build( );

        List<Payment> payments = buildPayments( sale, saleDb );
        saleDb.setPayments( payments );

        List<SaleItem> items = buildSaleItems( sale, saleDb );
        saleDb.setItems( items );

        saleRepository.save( saleDb );
    }

    private List<SaleItem> buildSaleItems( SaleDto sale,
                                           Sale saleDb ) {
        return sale.getItems( ).stream( )
                .map( saleItemDto -> SaleItem.builder( )
                        .sku( saleItemDto.getSku( ) )
                        .total( saleItemDto.getTotal( ) )
                        .quantity( saleItemDto.getQuantity( ) )
                        .sale( saleDb )
                        .build( ) )
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
    public SaleDto getSaleById( Long id ) {
        Sale saleDb = saleRepository.findById( id )
                .orElseThrow( );
        return saleDb.toSale( );
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
