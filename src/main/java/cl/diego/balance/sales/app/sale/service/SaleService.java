package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.repository.domain.Sale;

public interface SaleService {

    void registerSale( SaleDto sale );

    SaleDto getSaleById( Long id );

    SaleDetailDto getSaleDetailByCategory( );

}
