package app.disney.ports.input.rs.controller;

import app.disney.common.security.service.AuthenticationService;
import app.disney.domain.model.User;
import app.disney.domain.usercase.UserService;
import app.disney.ports.input.rs.mapper.AuthenticationControllerMapper;
import app.disney.ports.input.rs.request.AuthenticationRequest;
import app.disney.ports.input.rs.request.CreateUserRequest;
import app.disney.ports.input.rs.response.AuthenticationResponse;
import app.disney.ports.input.rs.response.RegisterResponse;
import app.disney.ports.input.rs.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    private final AuthenticationControllerMapper authenticationControllerMapper;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        User user = authenticationControllerMapper.createUserRequestToUser(userRequest);
        User createdUser = userService.createUser(user);
        UserResponse userResponse = authenticationControllerMapper.userToUserResponse(createdUser);
        String jwt = authService.singIn(userRequest.getEmail(), userRequest.getPassword());
        return new ResponseEntity<>(new RegisterResponse(userResponse, new AuthenticationResponse(jwt)),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@Valid @RequestBody AuthenticationRequest authRequest) {

        String jwt = authService.singIn(authRequest.getEmail(), authRequest.getPassword());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse meData(@AuthenticationPrincipal User user) {
        return authenticationControllerMapper.userToUserResponse(user);
    }

}
