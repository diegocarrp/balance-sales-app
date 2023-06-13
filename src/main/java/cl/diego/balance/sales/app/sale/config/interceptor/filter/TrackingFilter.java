package cl.diego.balance.sales.app.sale.config.interceptor.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class TrackingFilter implements Filter {

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String trackingId = request.getHeader( "X-trackingId" );
        trackingId = trackingId != null ? trackingId : UUID.randomUUID().toString();
        MDC.put("trackingId", trackingId);
        filterChain.doFilter( servletRequest, servletResponse );
    }
}