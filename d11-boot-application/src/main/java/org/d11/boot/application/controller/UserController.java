package org.d11.boot.application.controller;

import org.d11.boot.api.UsersApi;
import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the UsersApi and provides user endpoints.
 */
@RestController
public class UserController extends AbstractRepositoryServiceController<UserDTO, UserService> implements UsersApi {

    /**
     * Creates a new controller.
     *
     * @param userService The repository service this controller will use.
     */
    @Autowired
    public UserController(final UserService userService) {
        super(userService);
    }

    @Override
    public ResponseEntity<UserDTO> findUserById(final Long userId) {
        return findById(userId);
    }

}
