package cl.diego.balance.sales.app.item.controller;

import cl.diego.balance.sales.app.item.dto.CategoryDto;
import cl.diego.balance.sales.app.item.service.ItemCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/category" )
@Slf4j
@AllArgsConstructor
public class CategoryController {

    private final ItemCategoryService categoryService;

    @PostMapping( "/create" )
    public ResponseEntity<CategoryDto> createItem( @RequestBody CategoryDto category ) {
        log.info( "CategoryController.createCategory - category: {}", category );
        categoryService.saveCategory( category );
        return new ResponseEntity<>( category, HttpStatus.CREATED );
    }

    @GetMapping( "/by-description/{description}" )
    public ResponseEntity<CategoryDto> getCategoryByDescription( @PathVariable String description ) {
        log.info( "CategoryController.getCategoryByDescription - description: <{}>", description );
        CategoryDto categoryFound = categoryService.getCategoryByDescription( description );
        return ResponseEntity.ok( categoryFound );
    }

    @GetMapping( "/by-id/{id}" )
    public ResponseEntity<CategoryDto> getCategoryById( @PathVariable Long id ) {
        log.info( "CategoryController.getCategoryById - id: <{}>", id );
        CategoryDto categoryFound = categoryService.getCategoryById( id );
        return ResponseEntity.ok( categoryFound );
    }

}
