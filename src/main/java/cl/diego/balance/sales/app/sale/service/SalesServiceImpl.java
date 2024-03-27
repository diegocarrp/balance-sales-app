package cl.diego.balance.sales.app.sale.service;

import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryRequest;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryResponse;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {
    @Override
    public void registerSale( SaleDto sale ) {

    }

    @Override
    public SalesSummaryResponse generateSummary(SalesSummaryRequest summaryRequest) {
        return null;
    }
}
