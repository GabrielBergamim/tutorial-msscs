package br.com.example.msscgateway.filters;

import br.com.example.msscgateway.config.client.AuthenticationClient;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends AbstractGatewayFilterFactory {

    private final RouteValidator validator;
    private final AuthenticationClient authClient;

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                String token = Optional.ofNullable(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION))
                        .map(headers -> headers.get(0))
                        .orElseThrow(RuntimeException::new);

                return authClient.validate(token)
                        .map(response -> exchange)
                        .flatMap(chain::filter);
            }

            return chain.filter(exchange);
        });
    }
}
