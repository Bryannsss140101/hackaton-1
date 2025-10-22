package org.idea.hackaton1.security.auth.service;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.security.auth.dto.LoginRequestDTO;
import org.idea.hackaton1.security.auth.dto.LoginResponseDTO;
import org.idea.hackaton1.user.domain.Role;
import org.modelmapper.ModelMapper;
import org.idea.hackaton1.security.auth.dto.RegisterRequestDTO;
import org.idea.hackaton1.security.auth.dto.RegisterResponseDTO;
import org.idea.hackaton1.security.jwt.JwtService;
import org.idea.hackaton1.user.domain.User;
import org.idea.hackaton1.user.infraestructure.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    //Transaccion porque puede que quede guardado o no // Register

    public RegisterResponseDTO register(RegisterRequestDTO dto) {

        // Validar el rol
        if (dto.getRole() == Role.ROLE_BRANCH && (dto.getBranch() == null || dto.getBranch().isBlank())) {
            throw new IllegalArgumentException("Debe especificar una sucursal (branch) para usuarios con rol BRANCH.");
        }

        if (dto.getRole() == Role.ROLE_CENTRAL) {
            dto.setBranch(null); // CENTRAL no tiene branch
        }

        // Verificar que el usuario no exista
        userRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("El email ya está registrado.");
        });

        userRepository.findByUsername(dto.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("El username ya está en uso.");
        });

        // Crear usuario
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .branch(dto.getBranch())
                .createdAt(ZonedDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponseDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .branch(savedUser.getBranch())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Authentication auth = authenticationManager.authenticate(
                // Pasa el email, no el username
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        User user = (User) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .expiresIn(3600L)
                .role(user.getRole())
                .branch(user.getBranch())
                .build();
    }

}
