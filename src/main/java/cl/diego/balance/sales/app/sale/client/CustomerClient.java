package cl.diego.balance.sales.app.sale.client;

import cl.diego.balance.sales.app.sale.client.dto.CustomerDto;
import cl.diego.balance.sales.app.sale.config.feign.CustomerClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customers", configuration = CustomerClientConfig.class )
public interface CustomerClient {

    @RequestMapping( method = RequestMethod.GET , value = "/by-id/{id}" )
    CustomerDto findById( @PathVariable Long id );

}
