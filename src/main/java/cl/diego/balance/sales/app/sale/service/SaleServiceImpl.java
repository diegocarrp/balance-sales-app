package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.item.repository.ItemCategoryRepository;
import cl.diego.balance.sales.app.item.repository.ItemRepository;
import cl.diego.balance.sales.app.item.repository.domain.Item;
import cl.diego.balance.sales.app.item.repository.domain.ItemCategory;
import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDetailItemDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.repository.PaymentMethodRepository;
import cl.diego.balance.sales.app.sale.repository.SaleItemRepository;
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

    private final SaleRepository          saleRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ItemCategoryRepository  itemCategoryRepository;
    private final SaleItemRepository      saleItemRepository;
    private final ItemRepository          itemRepository;

    public SaleServiceImpl( SaleRepository saleRepository,
                            PaymentMethodRepository paymentMethodRepository,
                            ItemCategoryRepository itemCategoryRepository,
                            SaleItemRepository saleItemRepository,
                            ItemRepository itemRepository ) {
        this.saleRepository          = saleRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.itemCategoryRepository  = itemCategoryRepository;
        this.saleItemRepository      = saleItemRepository;
        this.itemRepository          = itemRepository;
    }

    @Override
    public void registerSale( SaleDto sale ) {

        Sale saleDb = Sale.builder( )
                .customerId( sale.getCustomerId( ) )
                .cashierId( sale.getCashierId( ) )
                .datetime( sale.getDatetime( ) )
                .totalAmount( sale.getTotalAmount( ) )
                .build( );

        List<Payment> payments = sale.getPayments( ).stream( )
                .map( p -> {
                    PaymentMethod paymentMethod = paymentMethodRepository.findById( p.getPaymentMethodId( ) )
                            .orElseThrow( );
                    return Payment.builder( )
                            .amount( p.getAmount( ) )
                            .paymentMethod( paymentMethod )
                            .sale( saleDb )
                            .build( );
                } ).collect( Collectors.toList( ) );
        saleDb.setPayments( payments );

        List<SaleItem> items = sale.getItems( ).stream( )
                .map( saleItemDto -> SaleItem.builder( )
                        .sku( saleItemDto.getSku( ) )
                        .total( saleItemDto.getTotal( ) )
                        .quantity( saleItemDto.getQuantity( ) )
                        .sale( saleDb )
                        .build( ) )
                .collect( Collectors.toList( ) );

        saleDb.setItems( items );
        saleRepository.save( saleDb );
    }

    @Override
    public SaleDto getSaleById( Long id ) {
        Sale saleDb = saleRepository.findById( id )
                .orElseThrow( );
        return saleDb.toSale( );
    }

    @Override
    public SaleDetailDto getSaleDetailByCategory( ) {
        List<SaleItem> saleItems = saleItemRepository.findAll( );

        List<ItemCategory> categories = itemCategoryRepository.findAll( );

        List<SaleDetailItemDto> detailItems = categories.stream( ).map( cat -> {
            BigDecimal categoryTotal = saleItems.stream( ).filter( saleItem -> {
                        Item itemFound = itemRepository.findBySku( saleItem.getSku( ) )
                                .orElseThrow( );
                        return itemFound.getItemCategoryId( ).equals( cat );
                    } ).map( SaleItem::getTotal )
                    .reduce( BigDecimal.ZERO, BigDecimal::add );

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
