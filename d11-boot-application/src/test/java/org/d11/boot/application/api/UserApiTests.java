package org.d11.boot.application.api;

import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.d11.boot.client.api.UserApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User API tests.
 */
public class UserApiTests extends AbstractApiTests {

    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * List of users.
     */
    private List<User> users;

    /**
     * Sets up mocked users for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.users = this.userRepository.findAll();
    }

    /**
     * Tests the findUserById API operation.
     */
    @Test
    public void findUserById() {
        final UserApi stadiumApi = new UserApi(getApiClient());

        assertFalse(this.users.isEmpty(), "Control users should not be empty.");

        for(final User user : this.users) {
            final UserDTO result = stadiumApi.findUserById(user.getId()).block();
            final UserDTO userDTO = map(user, UserDTO.class);

            assertNotNull(result, "User by id should not be null.");
            assertEquals(userDTO, result, "User by id should equal User.");
        }

        assertNotFound(stadiumApi.findUserById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
