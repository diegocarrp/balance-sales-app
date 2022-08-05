package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SaleResponseDto;

import java.time.LocalDateTime;

public interface SaleService {

    void registerSale( SaleDto sale );

    SaleResponseDto getSaleById( Long id );

    SaleDetailDto getSaleDetailByCategory( LocalDateTime startDate, LocalDateTime endDate );

}
