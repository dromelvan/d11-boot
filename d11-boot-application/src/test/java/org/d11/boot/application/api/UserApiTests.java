package org.d11.boot.application.api;

import org.d11.boot.api.model.UserDTO;
import org.d11.boot.api.service.UserApiService;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * User API tests.
 */
public class UserApiTests extends AbstractRepositoryApiTests<User, UserRepository, UserApiService> {

    /**
     * Tests the findUserById API operation.
     */
    @Test
    public void findUserById() {
        for(final User user : getEntities()) {
            final UserDTO result = getApiService().findUserById(user.getId());
            final UserDTO userDTO = map(user, UserDTO.class);

            assertNotNull(result, "User by id should not be null.");
            assertEquals(userDTO, result, "User by id should equal User.");
        }

        assertNull(getApiService().findUserById(-1L), "User not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
