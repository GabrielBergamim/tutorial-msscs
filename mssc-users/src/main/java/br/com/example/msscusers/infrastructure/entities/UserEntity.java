package br.com.example.msscusers.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, unique = true, length=50)
    private String email;

    @Column(nullable=false)
    private String password;
}
