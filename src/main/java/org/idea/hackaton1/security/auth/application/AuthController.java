package org.idea.hackaton1.security.auth.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.security.auth.dto.LoginRequestDTO;
import org.idea.hackaton1.security.auth.dto.LoginResponseDTO;
import org.idea.hackaton1.security.auth.dto.RegisterRequestDTO;
import org.idea.hackaton1.security.auth.dto.RegisterResponseDTO;
import org.idea.hackaton1.security.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequestDTO));
    }
}
