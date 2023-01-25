package br.com.example.msscauth.domain.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {
    private String email;
    private String password;
}
