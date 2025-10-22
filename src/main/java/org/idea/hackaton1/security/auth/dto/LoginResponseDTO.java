package org.idea.hackaton1.security.auth.dto;

import lombok.Builder;
import lombok.Data;
import org.idea.hackaton1.user.domain.Role;

@Data
@Builder
public class LoginResponseDTO {
    private Long expiresIn;
    private String token;
    private Role role;
    private String branch;
}
