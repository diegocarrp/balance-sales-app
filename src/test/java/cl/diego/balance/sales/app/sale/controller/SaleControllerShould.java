package cl.diego.balance.sales.app.sale.controller;

import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;
import cl.diego.balance.sales.app.sale.service.SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.util.List;

import static cl.diego.balance.commons.testing.UtilForTesting.getMappedObjectFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( SaleController.class )
public class SaleControllerShould {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SaleService saleService;

    private static SaleRequest saleRequest;
    private static SaleDto     saleDto;

    private static final String SALE_ONE     = "sale/saleOne.json";
    private static final String SALE_ONE_DTO = "sale/saleDtoOne.json";

    private static ObjectMapper mapper;

    @BeforeAll
    static void setUp( ) throws IOException {
        saleRequest = getMappedObjectFromFile( SALE_ONE, SaleRequest.class );
        saleDto     = getMappedObjectFromFile( SALE_ONE_DTO, SaleDto.class );

        mapper = new ObjectMapper( ).registerModule( new JavaTimeModule( ) );
    }

    @Test
    void registerSale_givenValidRequest( ) throws Exception {
        when( saleService.registerSale( saleRequest ) )
                .thenReturn( saleDto );

        mvc.perform( post( "/sale/register" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( mapper.writeValueAsString( saleRequest ) ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( jsonPath( "$.customerId" ).value( 123 ) );
    }

    @Test
    void throwException_givenInvalidRequest( ) {
        Exception exception =
                new ApiValidationException( "Error doing test.", List.of( "Error 1 - Error description" ) );
        when( saleService.registerSale( saleRequest ) )
                .thenThrow( exception );

        NestedServletException e = assertThrows( NestedServletException.class, ( ) -> {
            mvc.perform( post( "/sale/register" )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( mapper.writeValueAsString( saleRequest ) ) );
        } );

        assertEquals( e.getCause( ).getClass( ), ApiValidationException.class );
    }
}
