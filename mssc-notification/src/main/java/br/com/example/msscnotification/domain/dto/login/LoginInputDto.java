package br.com.example.msscnotification.domain.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {
    private String email;
    private String password;
}
