package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.d11.boot.client.api.UserApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * User API tests.
 */
public class UserApiTests extends AbstractRepositoryApiTests<User, UserRepository> {

    /**
     * Tests the findUserById API operation.
     */
    @Test
    public void findUserById() {
        final UserApi userApi = getApi(UserApi.class);
        for(final User user : getEntities()) {
            final UserDTO result = userApi.findUserById(user.getId());
            final UserDTO userDTO = map(user, UserDTO.class);

            assertNotNull(result, "User by id should not be null.");
            assertEquals(userDTO, result, "User by id should equal User.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> userApi.findUserById(-1L),
                     "User not found should throw NotFound exception.");
    }

}
