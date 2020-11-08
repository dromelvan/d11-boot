package org.d11.boot.api.service;

import org.d11.boot.api.model.UserDTO;
import org.d11.boot.client.api.UserApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides user API services.
 */
@Service
public class UserApiService extends D11ApiService {

    /**
     * Finds a user with a specific id.
     *
     * @param userId The id of the user that will be looked up.
     * @return user DTO for the specified id or null if no user was found.
     */
    public UserDTO findUserById(final Long userId) {
        try {
            final UserApi userApi = new UserApi(getApiClient());
            return userApi.findUserById(userId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
