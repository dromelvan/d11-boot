package org.d11.boot.application.service;

import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.configuration.CacheConfiguration;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Provides user services.
 */
@Service
public class UserService extends AbstractRepositoryService<User, UserDTO, UserRepository> implements UserDetailsService {

    /**
     * Creates a new service.
     *
     * @param userRepository The repository this service will use.
     */
    @Autowired
    public UserService(final UserRepository userRepository) {
        super(userRepository);
    }

    /**
     * Loads user details from cache if they are cached or from the database if not.
     *
     * @param username Username for the user details that will be loaded.
     * @return User details for the user with the provided username.
     */
    @Cacheable(CacheConfiguration.USER_DETAILS_CACHE)
    public UserDetails loadCachedUserByUsername(final String username) {
        return loadUserByUsername(username);
    }

    @Override
    @CacheEvict(value = CacheConfiguration.USER_DETAILS_CACHE, key = "#username")
    public UserDetails loadUserByUsername(final String username) {
        final User user = getJpaRepository()
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with provided email found."));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                // TODO: Authorities
                .roles(user.isAdministrator() ? new String[] { "USER", "ADMIN"  } : new String[] { "USER" })
                .password(user.getEncryptedPassword())
                .build();
    }

}
