package app.disney.ports.input.rs.controller;


import app.disney.domain.model.AppUser;
import app.disney.domain.usercase.IUserService;
import app.disney.ports.input.rs.mapper.UserControllerMapper;
import app.disney.ports.input.rs.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;


    private final UserControllerMapper userControllerMapper;

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {

        AppUser user = userControllerMapper.updateUserRequestToUser(updateUserRequest);

        userService.updateEntityIfExists(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Valid @NotNull @PathVariable Long id) {
        userService.deleteUserById(id);

    }
}
