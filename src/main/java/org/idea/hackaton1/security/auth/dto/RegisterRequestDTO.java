package org.idea.hackaton1.security.auth.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.idea.hackaton1.user.domain.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequestDTO {
        @NotBlank
        private String username;
        @Email
        @NotBlank
        private String email;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
            message = "Debe contener al menos una mayúscula, una minúscula, un número y un símbolo especial"
        )
        private String password;

        @NotNull(message = "El rol es obligatorio")
        private Role role;

        private String branch;
}
