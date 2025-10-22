package org.idea.hackaton1.user.dto;

import lombok.Data;
import org.idea.hackaton1.user.domain.Role;

import java.time.ZonedDateTime;

@Data
public class UserResponseDTO {

    private Long id;

    private String username;

    private String email;

    private Role role;

    private String branch;

    private ZonedDateTime createdAt;
}
