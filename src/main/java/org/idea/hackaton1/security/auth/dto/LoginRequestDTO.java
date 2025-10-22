package org.idea.hackaton1.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginRequestDTO {
    @NotBlank(message = "El username es obligatorio")
    @Email
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
            message = "Debe contener al menos una mayúscula, una minúscula, un número y un símbolo especial"
    )
    private String password;
}
