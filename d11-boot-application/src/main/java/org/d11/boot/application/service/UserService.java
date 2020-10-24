package org.d11.boot.application.service;

import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides user services.
 */
@Service
public class UserService extends AbstractRepositoryService<User, UserDTO, UserRepository> {

    /**
     * Creates a new service.
     *
     * @param userRepository The repository this service will use.
     */
    @Autowired
    public UserService(final UserRepository userRepository) {
        super(userRepository);
    }

}
