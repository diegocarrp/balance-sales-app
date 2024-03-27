package cl.diego.balance.sales.app.sale.controller;

import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryRequest;
import cl.diego.balance.sales.app.sale.dto.SalesSummaryResponse;
import cl.diego.balance.sales.app.sale.service.SalesService;
import cl.diego.balance.sales.app.sale.dto.SaleResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping( "/sales" )
@AllArgsConstructor
@Slf4j
public class SalesController {

    private final SalesService salesService;

    @PostMapping( "/register" )
    public ResponseEntity<SaleDto> registerSale( @RequestBody SaleRequest saleRequest ) {
        log.info( "Registering sale with body: <{}>", saleRequest );

        SaleDto response = salesService.registerSale( saleRequest );
        return ResponseEntity.ok( ).build( );
    }

    @PostMapping( "/summary" )
    public ResponseEntity<SalesSummaryResponse> SalesSummary(@RequestBody SalesSummaryRequest summaryRequest ) {
        log.info( "Getting summary with body: <{}>", summaryRequest );

        SalesSummaryResponse response = salesService.generateSummary( summaryRequest );
        return ResponseEntity.ok( response );
    }

    @GetMapping( "/by-id/{id}" )
    public ResponseEntity<SaleResponseDto> registerSale( @PathVariable Long id ) {
        log.info( "Looking for sale with id: <{}>", id );

        SaleResponseDto saleFound = salesService.getSaleById( id );
        return ResponseEntity.ok( saleFound );
    }

    @GetMapping( "/by-category" )
    public ResponseEntity<SaleDetailDto> getSaleDetailByCategory( @RequestParam @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss" ) LocalDateTime startDate,
                                                                  @RequestParam( required = false ) @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss" ) LocalDateTime endDate ) {
        log.info( "Looking for sale detail by categories" );
        SaleDetailDto saleDetailByCategory = salesService.getSaleDetailByCategory( startDate, endDate );
        return ResponseEntity.ok( saleDetailByCategory );
    }

}
