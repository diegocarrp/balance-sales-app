package cl.diego.balance.sales.app.sale.client;

import cl.diego.balance.sales.app.sale.client.dto.CustomerDto;
import cl.diego.balance.sales.app.sale.config.feign.CustomerClientConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customers" , configuration = CustomerClientConfig.class, url = "${balance-sales.config.customer.endpoint}")
public interface CustomerClient {

    @GetMapping
    @RequestLine( "GET /by-id/{id}" )
    CustomerDto findById( @PathVariable Long id );

}
