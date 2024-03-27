package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryRequest;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryResponse;

public interface SalesService {

    void registerSale( SaleDto sale );

    SalesSummaryResponse generateSummary(SalesSummaryRequest summaryRequest);
}
