package br.com.example.msscnotification.infrastructure.services;

import br.com.example.msscnotification.domain.dto.login.LoginInputDto;
import br.com.example.msscnotification.domain.dto.user.UserOutputDto;
import br.com.example.msscnotification.domain.model.AuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;

    public AuthorizationToken login(@RequestBody LoginInputDto loginInputDto) {
        HttpEntity<LoginInputDto> entity = new HttpEntity<>(loginInputDto);

        ResponseEntity<AuthorizationToken> authResponse = restTemplate
                .exchange("http://localhost:8080/auth/api/auth/login", HttpMethod.POST, entity, AuthorizationToken.class);

        if (!authResponse.getStatusCode().is2xxSuccessful() || authResponse.getBody() == null) {
            throw new RuntimeException("Unauthorized");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authResponse.getBody().getAccessToken());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<UserOutputDto> userDetails = restTemplate
                .exchange("http://localhost:8080/auth/api/auth/userDetails", HttpMethod.GET, httpEntity, UserOutputDto.class);

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getBody().getEmail(),
                authResponse.getBody().getAccessToken(),
                userDetails.getBody().getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        return authResponse.getBody();
    }
}