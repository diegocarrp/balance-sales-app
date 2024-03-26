package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SaleResponseDto;
import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;

import java.time.LocalDateTime;

public interface SaleService {

    SaleDto registerSale( SaleRequest sale );

    SaleResponseDto getSaleById( Long id );

    SaleDetailDto getSaleDetailByCategory( LocalDateTime startDate,
                                           LocalDateTime endDate );

}
