package cl.diego.balance.sales.app.item.controller;

import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/item" )
@Slf4j
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping( "/create" )
    public ResponseEntity<ItemDto> createItem( @RequestBody ItemDto item ) {
        log.info( "ItemController.createItem - item: {}", item );
        itemService.saveItem( item );
        return new ResponseEntity<>( item, HttpStatus.CREATED );
    }

    @GetMapping( "/sky/{sku}" )
    public ResponseEntity<ItemDto> getItemBySku( @PathVariable String sku ) {
        log.info( "ItemController.getItemBySku - sku: <{}>", sku );
        ItemDto itemFound = itemService.getItemBySku( sku );
        return ResponseEntity.ok( itemFound );
    }

    @PutMapping( "/update" )
    public ResponseEntity<ItemDto> updateItem( @RequestBody ItemDto item ) {
        log.info( "ItemController.updateItem - body: <{}>", item );
        itemService.updateItem( item );
        return ResponseEntity.ok( ).build( );
    }

    @DeleteMapping( "/id/{id}" )
    public ResponseEntity<Void> deleteItem( @PathVariable Long id ) {
        log.info( "ItemController.deleteItem - id: <{}>", id );
        itemService.deleteItem( id );
        return ResponseEntity.ok( ).build( );
    }

}
