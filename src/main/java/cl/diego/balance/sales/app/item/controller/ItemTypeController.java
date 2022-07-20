package cl.diego.balance.sales.app.item.controller;

import cl.diego.balance.sales.app.item.dto.ItemTypeDto;
import cl.diego.balance.sales.app.item.service.ItemTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/item-type" )
@Slf4j
@AllArgsConstructor
public class ItemTypeController {

    private final ItemTypeService itemTypeService;

    @PostMapping( "/create" )
    public ResponseEntity<ItemTypeDto> createItemType( @RequestBody ItemTypeDto itemType ) {
        log.info( "ItemTypeController.createItemType - itemType: {}", itemType );
        itemTypeService.saveItemType( itemType );
        return new ResponseEntity<>( itemType, HttpStatus.CREATED );
    }

    @GetMapping( "/description/{description}" )
    public ResponseEntity<ItemTypeDto> getCategoryByDescription( @PathVariable String description ) {
        log.info( "CategoryController.getCategoryByDescription - description: <{}>", description );
        ItemTypeDto itemTypeFound = itemTypeService.getItemTypeByDescription( description );
        return ResponseEntity.ok( itemTypeFound );
    }

    @GetMapping( "/id/{id}" )
    public ResponseEntity<ItemTypeDto> getCategoryById( @PathVariable Long id ) {
        log.info( "CategoryController.getCategoryById - id: <{}>", id );
        ItemTypeDto itemTypeFound = itemTypeService.getItemTypeById( id );
        return ResponseEntity.ok( itemTypeFound );
    }

}
