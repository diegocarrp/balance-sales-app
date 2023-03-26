package cl.diego.balance.sales.app.sale.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

public class TrackingInterceptor implements RequestInterceptor {

    @Override
    public void apply( RequestTemplate template ) {
        template.header( "X-cmRef", MDC.get( "trackingId" ) );
    }
}
