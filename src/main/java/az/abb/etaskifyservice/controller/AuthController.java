package az.abb.etaskifyservice.controller;

import az.abb.etaskifyservice.config.Constants;
import az.abb.etaskifyservice.entity.Organisation;
import az.abb.etaskifyservice.exceptions.UsernameAlreadyExist;
import az.abb.etaskifyservice.requests.LoginRequest;
import az.abb.etaskifyservice.responses.AuthResponse;
import az.abb.etaskifyservice.responses.Response;
import az.abb.etaskifyservice.requests.SignupRequest;
import az.abb.etaskifyservice.entity.User;
import az.abb.etaskifyservice.service.AuthService;
import az.abb.etaskifyservice.service.OrganisationService;
import az.abb.etaskifyservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    private final OrganisationService organisationService;

    @PostMapping("/signup")
    ResponseEntity<Response> signup(@RequestBody @Valid SignupRequest request) {
        try {
            Organisation organisation = organisationService.save(request);

            User user = userService.registerUser(request, organisation);

            AuthResponse authData = authService.generateToken(user);

            Response response = Response.builder()
                    .message(Constants.TOKEN_GENERATED)
                    .data(authData)
                    .build();

            return ResponseEntity.ok(response);
        } catch (UsernameAlreadyExist e) {
            Response response = Response.builder()
                    .message(Constants.REQUEST_FAILED).build()
                    .setErrorData(e.getKey(), e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    ResponseEntity<Response> login(@RequestBody @Valid LoginRequest request) {
        try {
            AuthResponse authData = authService.authenticate(request);

            return ResponseEntity.ok(Response.builder().message(Constants.TOKEN_GENERATED).data(authData).build());
        } catch (Exception e) {
            System.out.println(e.getMessage());

            Response response = Response.builder()
                    .message(Constants.REQUEST_FAILED).build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
