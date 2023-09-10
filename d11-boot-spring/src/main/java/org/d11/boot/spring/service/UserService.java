package org.d11.boot.spring.service;

import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User service.
 */
@Service
public class UserService extends RepositoryService<User, UserRepository> implements UserDetailsService {

    /**
     * Name of the user cache defined in application.yaml.
     */
    private static final String USER_DETAILS_CACHE = "userDetailsCache";

    /**
     * Creates a new user service.
     *
     * @param userRepository  The repository the service will use.
     */
    @Autowired
    public UserService(final UserRepository userRepository) {
        super(User.class, userRepository);
    }

    /**
     * Loads user details from cache if they are cached or from the database if not.
     *
     * @param username Username for the user details that will be loaded.
     * @return User details for the user with the provided username.
     */
    @Cacheable(USER_DETAILS_CACHE)
    public UserDetails loadCachedUserByUsername(final String username) {
        return loadUserByUsername(username);
    }

    @Override
    @CacheEvict(value = USER_DETAILS_CACHE, key = "#username")
    public UserDetails loadUserByUsername(final String username) {
        final String userRole = "USER";
        final String adminRole = "ADMIN";
        final User user = getJpaRepository()
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Authentication failed"));
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            // TODO: Authorities
            .roles(user.isAdministrator() ? new String[]{userRole, adminRole} : new String[]{userRole})
            .password(user.getEncryptedPassword())
            .build();
    }

}
