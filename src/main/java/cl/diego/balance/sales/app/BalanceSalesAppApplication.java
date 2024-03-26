package cl.diego.balance.sales.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BalanceSalesAppApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BalanceSalesAppApplication.class, args );
    }

}
