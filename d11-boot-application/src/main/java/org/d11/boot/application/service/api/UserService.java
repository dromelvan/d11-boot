package org.d11.boot.application.service.api;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.configuration.CacheConfiguration;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.d11.boot.application.util.BadRequestException;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Provides user services.
 */
@Service
public class UserService extends ApiRepositoryService<User, UserDTO, UserRepository> implements UserDetailsService {

    /**
     * Password encoder to encode passwords for new users.
     */
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
     * Creates a new user.
     *
     * @param name             User name.
     * @param email            User email.
     * @param password         User password.
     * @param repeatedPassword User password, repeated.
     * @return Newly created user.
     */
    @SuppressWarnings("PMD.UseObjectForClearerAPI")
    public UserDTO createUser(final String name,
                              final String email,
                              final String password,
                              final String repeatedPassword) {
        if (getJpaRepository().findByName(email).isPresent()) {
            throw new BadRequestException("Invalid name");
        }

        if (getJpaRepository().findByEmail(email).isPresent()) {
            throw new BadRequestException("Invalid email");
        }

        if (StringUtils.isAnyBlank(password, repeatedPassword)
                || !StringUtils.equals(password, repeatedPassword)) {
            throw new BadRequestException("Passwords are invalid or do not match");
        }

        final User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setEncryptedPassword(this.passwordEncoder.encode(password));
        user.setAdministrator(false);

        return map(getJpaRepository().save(user));
    }

    /**
     * Updates the password for a user.
     *
     * @param password         The password.
     * @param repeatedPassword User password, repeated.
     */
    public void updatePassword(final String password, final String repeatedPassword) {
        if (StringUtils.isAnyBlank(password, repeatedPassword)
                || !StringUtils.equals(password, repeatedPassword)) {
            throw new BadRequestException("Passwords are invalid or do not match");
        }

        final User user = getCurrentUser().orElseThrow(NotFoundException::new);
        user.setEncryptedPassword(this.passwordEncoder.encode(password));

        getJpaRepository().save(user);
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
        final String userRole = "USER";
        final String adminRole = "ADMIN";
        final User user = getJpaRepository()
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with provided email found."));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                // TODO: Authorities
                .roles(user.isAdministrator() ? new String[] { userRole, adminRole } : new String[] { userRole })
                .password(user.getEncryptedPassword())
                .build();
    }

}
