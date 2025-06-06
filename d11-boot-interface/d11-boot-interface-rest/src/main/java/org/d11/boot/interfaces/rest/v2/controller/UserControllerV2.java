package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.UserApi;
import org.d11.boot.api.v2.model.ConfirmUserRequestBodyDTO;
import org.d11.boot.api.v2.model.CreateUserRequestBodyDTO;
import org.d11.boot.api.v2.model.UpdateUserRequestBodyDTO;
import org.d11.boot.api.v2.model.UserDTO;
import org.d11.boot.api.v2.model.UserResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.model.UserConfirmation;
import org.d11.boot.spring.model.UserRegistration;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.security.RoleUser;
import org.d11.boot.spring.service.UserService;
import org.d11.boot.util.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * User API REST controller implementation.
 */
@RestController
public class UserControllerV2 extends RepositoryServiceController<UserService> implements UserApi {

    /**
     * Create a new controller.
     *
     * @param userService The service the controller will use.
     */
    @Autowired
    public UserControllerV2(final UserService userService) {
        super(userService);
    }

    @Override
    public ResponseEntity<UserResponseBodyDTO> createUser(final CreateUserRequestBodyDTO createUserRequestBodyDTO) {
        final UserRegistration userRegistration = map(createUserRequestBodyDTO, UserRegistration.class);

        UserResponseBodyDTO userResponseBodyDTO;
        try {
            final User user = getRepositoryService().createUser(userRegistration);
            userResponseBodyDTO = new UserResponseBodyDTO()
                    .user(map(user, UserDTO.class));
        } catch (final ConflictException e) {
            // Return CREATED even if there's a conflict to prevent user fishing
            userResponseBodyDTO = new UserResponseBodyDTO()
                    .user(new UserDTO()
                                  .name(createUserRequestBodyDTO.getName())
                                  .administrator(false));
        }
        return new ResponseEntity<>(userResponseBodyDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> confirmUser(final ConfirmUserRequestBodyDTO confirmUserRequestBodyDTO) {
        final UserConfirmation userConfirmation = map(confirmUserRequestBodyDTO, UserConfirmation.class);
        getRepositoryService().confirmUser(userConfirmation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @RoleUser
    public ResponseEntity<UserResponseBodyDTO> updateUser(final Long userId,
                                                          final UpdateUserRequestBodyDTO updateUserRequestBodyDTO) {
        final User user = getRepositoryService().updateUserPassword(userId,
                                                                    updateUserRequestBodyDTO.getCurrentPassword(),
                                                                    updateUserRequestBodyDTO.getPassword(),
                                                                    updateUserRequestBodyDTO.getConfirmPassword());
        final UserResponseBodyDTO userResponseBodyDTO = new UserResponseBodyDTO()
                .user(map(user, UserDTO.class));
        return ResponseEntity.ok(userResponseBodyDTO);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<Void> deleteUser(final Long userId) {
        getRepositoryService().deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
