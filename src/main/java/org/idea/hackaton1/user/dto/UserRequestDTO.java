package org.idea.hackaton1.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.idea.hackaton1.user.domain.Role;

@Data
public class UserRequestDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9._]{3,30}$",
            message = "El nombre de usuario debe tener entre 3 y 30 caracteres y solo puede contener letras, números, puntos y guiones bajos"
    )
    private String username;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private Role role;

    private String branch;



}
