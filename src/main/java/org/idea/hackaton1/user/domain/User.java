package org.idea.hackaton1.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(
            regexp = "^[A-Za-z0-9._]{3,30}$",
            message = "El nombre de usuario debe tener entre 3 y 30 caracteres y solo puede contener letras, números, puntos y guiones bajos"
    )
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String branch;

    private LocalDateTime createdAt;
}
