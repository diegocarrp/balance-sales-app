package cl.diego.balance.sales.app.sale.config.feign;

import cl.diego.balance.sales.app.sale.client.CustomerClient;
import cl.diego.balance.sales.app.sale.config.interceptor.TrackingInterceptor;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CustomerClientConfig {

    private final String url;
    private final Long   readTimeout;
    private final Long   connectionTimeout;

    public CustomerClientConfig( @Value( "${balance-sales.config.customer.endpoint}" ) String url,
                                 @Value( "${balance-sales.config.customer.read-timeout}" ) Long readTimeout,
                                 @Value( "${balance-sales.config.customer.connection-timeout}" ) Long connectionTimeout ) {
        this.url               = url;
        this.readTimeout       = readTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    @Bean
    public CustomerClient customerClient( ) {
        log.info( "Creando el customerClient" );
        return Feign.builder( )
                .encoder( new JacksonEncoder( List.of( new JavaTimeModule( ) ) ) )
                .decoder( new JacksonDecoder( ) )
                .client( new OkHttpClient( ) )
                .options( new Request.Options( connectionTimeout,
                        TimeUnit.SECONDS,
                        readTimeout,
                        TimeUnit.SECONDS,
                        false ) )
                .logger( new Slf4jLogger( ) )
                .logLevel( Logger.Level.FULL )
                .requestInterceptor( new TrackingInterceptor( ) )
                .target( CustomerClient.class, url );
    }


}
