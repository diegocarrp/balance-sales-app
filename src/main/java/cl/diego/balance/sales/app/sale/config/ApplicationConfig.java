package cl.diego.balance.sales.app.sale.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "balance-sales.config")
public class ApplicationConfig {

    private Api customer;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Api {
        private String endpoint;
    }
}
