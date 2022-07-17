package cl.diego.balance.sales.app.sale.controller;

import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/sale" )
@AllArgsConstructor
@Slf4j
public class SaleController {

    private final SaleService saleService;

    @PostMapping( "/register" )
    public ResponseEntity<Void> registerSale( @RequestBody SaleDto sale ) {
        log.info( "Registering sale with body: <{}>", sale );

        saleService.registerSale( sale );
        return ResponseEntity.ok( ).build( );
    }

    @GetMapping( "/id/{id}" )
    public ResponseEntity<SaleDto> registerSale( @PathVariable Long id ) {
        log.info( "Looking for sale with id: <{}>", id );

        SaleDto saleFound = saleService.getSaleById( id );
        return ResponseEntity.ok( saleFound );
    }


}
