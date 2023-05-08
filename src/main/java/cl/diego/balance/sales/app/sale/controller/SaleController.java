package cl.diego.balance.sales.app.sale.controller;

import cl.diego.balance.sales.app.sale.dto.SaleDetailDto;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.SaleResponseDto;
import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;
import cl.diego.balance.sales.app.sale.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping( "/sale" )
@AllArgsConstructor
@Slf4j
public class SaleController {

    private final SaleService saleService;

    @PostMapping( "/register" )
    public ResponseEntity<SaleDto> registerSale( @RequestBody SaleRequest saleRequest ) {
        log.info( "Registering sale with body: <{}>", saleRequest );

        SaleDto response = saleService.registerSale( saleRequest );
        return ResponseEntity.ok( response );
    }

    @GetMapping( "/by-id/{id}" )
    public ResponseEntity<SaleResponseDto> registerSale( @PathVariable Long id ) {
        log.info( "Looking for sale with id: <{}>", id );

        SaleResponseDto saleFound = saleService.getSaleById( id );
        return ResponseEntity.ok( saleFound );
    }

    @GetMapping( "/by-category" )
    public ResponseEntity<SaleDetailDto> getSaleDetailByCategory( @RequestParam @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss" ) LocalDateTime startDate,
                                                                  @RequestParam( required = false ) @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss" ) LocalDateTime endDate ) {
        log.info( "Looking for sale detail by categories" );
        SaleDetailDto saleDetailByCategory = saleService.getSaleDetailByCategory( startDate, endDate );
        return ResponseEntity.ok( saleDetailByCategory );
    }

}
