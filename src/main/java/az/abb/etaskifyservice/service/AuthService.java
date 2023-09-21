package az.abb.etaskifyservice.service;

import az.abb.etaskifyservice.entity.User;
import az.abb.etaskifyservice.exceptions.UsernameAlreadyExist;
import az.abb.etaskifyservice.mapper.UserMapper;
import az.abb.etaskifyservice.repository.UserRepository;
import az.abb.etaskifyservice.requests.LoginRequest;
import az.abb.etaskifyservice.requests.SignupRequest;
import az.abb.etaskifyservice.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthResponse generateToken(User user) {
        String accessToken = jwtService.generateToken(user);

        return AuthResponse.builder().accessToken(accessToken).build();
    }
    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
