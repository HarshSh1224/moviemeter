package com.moviemeter.moviemeter.services;

import com.moviemeter.moviemeter.dto.AuthResponse;
import com.moviemeter.moviemeter.dto.LoginRequest;
import com.moviemeter.moviemeter.dto.RegisterRequest;
import com.moviemeter.moviemeter.models.Role;
import com.moviemeter.moviemeter.models.User;
import com.moviemeter.moviemeter.repositories.UserRepository;
import com.moviemeter.moviemeter.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already in use");
        }

        var user = User.builder()
            .name(request.name())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(Role.USER)
            .build();

        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );

        var user = userRepository.findByEmail(request.email())
            .orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));

        return new AuthResponse(jwtService.generateToken(user));
    }
}
