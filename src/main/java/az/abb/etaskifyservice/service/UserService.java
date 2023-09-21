package az.abb.etaskifyservice.service;

import az.abb.etaskifyservice.entity.Organisation;
import az.abb.etaskifyservice.enums.UserRole;
import az.abb.etaskifyservice.requests.CreateUserRequest;
import az.abb.etaskifyservice.requests.SignupRequest;
import az.abb.etaskifyservice.entity.User;
import az.abb.etaskifyservice.exceptions.UsernameAlreadyExist;
import az.abb.etaskifyservice.mapper.UserMapper;
import az.abb.etaskifyservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Value("${auth.default_password}")
    private String defaultPassword;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public User registerUser(SignupRequest request, Organisation organisation) throws UsernameAlreadyExist {
        User user = UserMapper.INSTANCE.toUserFromSignupRequest(request);
        user.setOrganisation(organisation);
        user.setRole(UserRole.ADMIN);

        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UsernameAlreadyExist("email", "User is already exist with this email.");
        } else if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UsernameAlreadyExist("username", "User is already exist with this username.");
        }

        final String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public User createUser(CreateUserRequest request) throws UsernameAlreadyExist {
        User loggedUser = getLoggedUser();

        System.out.println("role: " + loggedUser.getRole());

        User user = UserMapper.INSTANCE.toUserFromCreateUserRequest(request);
        user.setPassword(defaultPassword);
        user.setUsername(request.getEmail());
        user.setOrganisation(loggedUser.getOrganisation());

        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UsernameAlreadyExist("email", "User is already exist with this email.");
        }

        final String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public User getLoggedUser() throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authenticationToken.getPrincipal();

        return user;
    }

    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }

}
