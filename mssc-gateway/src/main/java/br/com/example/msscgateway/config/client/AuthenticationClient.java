package br.com.example.msscgateway.config.client;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class AuthenticationClient {

    private final WebClient.Builder builder;

    public Mono<Void> validate(String token) {
        return buildClient().get().uri("/validate")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(Void.class);
    }

    private WebClient buildClient() {
        return builder
                .baseUrl("lb://mssc-auth/auth/api/auth")
                .build();
    }
}
