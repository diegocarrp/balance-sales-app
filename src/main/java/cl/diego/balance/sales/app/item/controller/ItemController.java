package cl.diego.balance.sales.app.item.controller;

import cl.diego.balance.sales.app.item.dto.ItemDto;
import cl.diego.balance.sales.app.item.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/item" )
@Slf4j
//@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    public ItemController( ItemService itemService ) {
        this.itemService = itemService;
        log.info( "ItemService: {}", itemService.getClass() );
    }

    @PostMapping( "/create" )
    public ResponseEntity<ItemDto> createItem( @RequestBody ItemDto item ) {
        log.info( "ItemController.createItem - item: {}", item );
        itemService.saveItem( item );
        return new ResponseEntity<>( item, HttpStatus.CREATED );
    }

    @GetMapping( "/by-sku/{sku}" )
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
    public ResponseEntity<Void> deleteItem( @PathVariable String id ) {
        log.info( "ItemController.deleteItem - id: <{}>", id );
        itemService.deleteItem( id );
        return ResponseEntity.ok( ).build( );
    }

}
