package org.idea.hackaton1.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.idea.hackaton1.user.domain.Role;

import java.time.ZonedDateTime;

@Data
@Builder
public class RegisterResponseDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String branch;
    private ZonedDateTime createdAt;
}
