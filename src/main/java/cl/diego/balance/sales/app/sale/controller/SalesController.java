package cl.diego.balance.sales.app.sale.controller;

import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryRequest;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryResponse;
import cl.diego.balance.sales.app.sale.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/sales" )
@AllArgsConstructor
@Slf4j
public class SalesController {

    private final SalesService salesService;

    @PostMapping( "/register" )
    public ResponseEntity<Void> registerSale( @RequestBody SaleDto sale ) {
        log.info( "Registering sale with body: <{}>", sale );

        salesService.registerSale( sale );
        return ResponseEntity.ok( ).build( );
    }

    @PostMapping( "/summary" )
    public ResponseEntity<SalesSummaryResponse> SalesSummary(@RequestBody SalesSummaryRequest summaryRequest ) {
        log.info( "Getting summary with body: <{}>", summaryRequest );

        SalesSummaryResponse response = salesService.generateSummary( summaryRequest );
        return ResponseEntity.ok( response );
    }


}
