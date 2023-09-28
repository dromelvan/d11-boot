package org.d11.boot.spring.service;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.model.UserRegistration;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
     * Confirm password property name.
     */
    private static final String CONFIRM_PASSWORD_PROPERTY = "confirmPassword";

    /**
     * Password encoder for encoding passwords for new users.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Creates a new user service.
     *
     * @param userRepository  The repository the service will use.
     * @param passwordEncoder Password encoder.
     */
    @Autowired
    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        super(User.class, userRepository);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user.
     *
     * @param userRegistration User registration properties.
     * @return The new user.
     */
    public User createUser(final UserRegistration userRegistration) {
        if (StringUtils.isBlank(userRegistration.getName())
                || getJpaRepository().findByName(userRegistration.getName()).isPresent()) {
            throw new BadRequestException("name", "Invalid name");
        }

        if (StringUtils.isBlank(userRegistration.getEmail())
                || getJpaRepository().findByEmail(userRegistration.getEmail()).isPresent()) {
            throw new BadRequestException("email", "Invalid email");
        }

        if (StringUtils.isBlank(userRegistration.getPassword())) {
            throw new BadRequestException("password", "Password is missing");
        }

        if (StringUtils.isBlank(userRegistration.getConfirmPassword())) {
            throw new BadRequestException(CONFIRM_PASSWORD_PROPERTY, "Password confirmation is missing");
        }

        if (!StringUtils.equals(userRegistration.getPassword(), userRegistration.getConfirmPassword())) {
            throw new BadRequestException(CONFIRM_PASSWORD_PROPERTY, "Password confirmation does not match password");
        }

        final User user = new User();
        user.setName(userRegistration.getName());
        user.setEmail(userRegistration.getEmail());
        user.setEncryptedPassword(this.passwordEncoder.encode(userRegistration.getPassword()));

        return getJpaRepository().save(user);
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
