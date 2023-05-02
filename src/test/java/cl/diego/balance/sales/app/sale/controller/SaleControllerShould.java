package cl.diego.balance.sales.app.sale.controller;

import cl.diego.balance.sales.app.sale.dto.SaleDto;
import cl.diego.balance.sales.app.sale.dto.request.SaleRequest;
import cl.diego.balance.sales.app.sale.service.SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static cl.diego.balance.commons.testing.UtilForTesting.asJsonString;
import static cl.diego.balance.commons.testing.UtilForTesting.getMappedObjectFromFile;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith( SpringExtension.class )
@ContextConfiguration( classes = SaleControllerShould.TestConfiguration.class )
class SaleControllerShould {

    static class TestConfiguration {
        @Bean
        public SaleController saleController( final SaleService saleService ) {
            return new SaleController( saleService );
        }
    }

    @MockBean
    private SaleService saleService;

    @Autowired
    private MockMvc        mockMvc;
    @Autowired
    private SaleController saleController;
    private ObjectMapper   mapper = new ObjectMapper( );

    private static SaleDto     saleOne;
    private static SaleRequest saleRequest;

    private static final String SALE_ONE = "sale/saleOne.json";


    @BeforeAll
    static void setUp( ) throws IOException {
        saleOne     = getMappedObjectFromFile( SALE_ONE, SaleDto.class );
        saleRequest = getMappedObjectFromFile( SALE_ONE, SaleRequest.class );
    }

    @Test
    void registerSale_givenValidRequest( ) throws Exception {
        when( saleService.registerSale( saleRequest ) ).thenReturn( saleOne );

        mockMvc.perform( post( "/sale/register" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( asJsonString( saleRequest ) ) )
                .andDo( print( ) )
                .andExpect( status( ).isOk( ) );
    }

    @Test
    void returnException_givenInvalidRequest( ) throws Exception {
        when( saleService.registerSale( saleRequest ) ).thenReturn( saleOne );

        mockMvc.perform( post( "/sale/register" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( asJsonString( saleRequest ) ) )
                .andDo( print( ) )
                .andExpect( status( ).isOk( ) );
    }
}