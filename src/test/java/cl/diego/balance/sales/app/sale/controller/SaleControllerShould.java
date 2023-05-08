package cl.diego.balance.sales.app.sale.controller;

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

import java.io.IOException;

import static cl.diego.balance.commons.testing.UtilForTesting.getMappedObjectFromFile;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
}
