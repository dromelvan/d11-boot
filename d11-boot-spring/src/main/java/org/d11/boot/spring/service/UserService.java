package org.d11.boot.spring.service;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.model.UserConfirmation;
import org.d11.boot.spring.model.UserRegistration;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.ConfirmRegistrationLinkMailMessage;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

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
     * Email property name.
     */
    private static final String EMAIL_PROPERTY = "email";

    /**
     * Password property name.
     */
    private static final String PASSWORD_PROPERTY = "password";

    /**
     * Confirm password property name.
     */
    private static final String CONFIRM_PASSWORD_PROPERTY = "confirmPassword";

    /**
     * Current password property name.
     */
    private static final String CURRENT_PASSWORD_PROPERTY = "currentPassword";

    /**
     * Is missing error message.
     */
    private static final String IS_MISSING_MESSAGE = "is missing";

    /**
     * Password encoder for encoding passwords for new users.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Confirm registration email sender.
     */
    private final JavaMailSender javaMailSender;

    /**
     * Creates a new user service.
     *
     * @param userRepository  The repository the service will use.
     * @param passwordEncoder Password encoder.
     * @param javaMailSender  Mail sender for confirm registration emails.
     */
    @Autowired
    public UserService(final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder,
                       final JavaMailSender javaMailSender) {
        super(User.class, userRepository);
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    /**
     * Creates a new user.
     *
     * @param userRegistration User registration properties.
     * @return The new user.
     */
    public User createUser(final UserRegistration userRegistration) {
        if (StringUtils.isBlank(userRegistration.getName())) {
            throw new BadRequestException("name", IS_MISSING_MESSAGE);
        }

        if (StringUtils.isBlank(userRegistration.getEmail())) {
            throw new BadRequestException(EMAIL_PROPERTY, IS_MISSING_MESSAGE);
        }

        if (StringUtils.isBlank(userRegistration.getConfirmRegistrationLink())) {
            throw new BadRequestException("confirmRegistrationLink", IS_MISSING_MESSAGE);
        }

        validatePassword(userRegistration.getPassword(), userRegistration.getConfirmPassword());

        if (getJpaRepository().findByName(userRegistration.getName()).isPresent()) {
            throw new ConflictException("Name is unavailable");
        }

        if (getJpaRepository().findByEmail(userRegistration.getEmail()).isPresent()) {
            throw new ConflictException("Email is unavailable");
        }

        final User user = new User();
        user.setName(userRegistration.getName());
        user.setEmail(userRegistration.getEmail());
        user.setEncryptedPassword(this.passwordEncoder.encode(userRegistration.getPassword()));
        user.setConfirmRegistrationToken(UUID.randomUUID());

        final User savedUser = getJpaRepository().save(user);

        final ConfirmRegistrationLinkMailMessage confirmRegistrationLinkMailMessage
                = new ConfirmRegistrationLinkMailMessage(user, userRegistration.getConfirmRegistrationLink());
        this.javaMailSender.send(confirmRegistrationLinkMailMessage);

        return savedUser;
    }

    /**
     * Confirms registration of a new user.
     *
     * @param userConfirmation User confirmation properties.
     */
    public void confirmUser(final UserConfirmation userConfirmation) {
        if (StringUtils.isBlank(userConfirmation.getEmail())) {
            throw new BadRequestException(EMAIL_PROPERTY, IS_MISSING_MESSAGE);
        }

        if (userConfirmation.getConfirmRegistrationToken() == null) {
            throw new BadRequestException("confirmRegistrationToken", IS_MISSING_MESSAGE);
        }

        final User user = getJpaRepository()
                .findByEmailAndConfirmRegistrationToken(userConfirmation.getEmail(),
                                                        userConfirmation.getConfirmRegistrationToken())
                .orElseThrow(UnauthorizedException::new);

        user.setConfirmRegistrationToken(null);
        getJpaRepository().save(user);
    }

    /**
     * Updates a user password.
     *
     * @param userId          ID of the user.
     * @param currentPassword Current user password.
     * @param password        New password.
     * @param confirmPassword New password confirmation.
     * @return The updated user.
     */
    public User updateUserPassword(final long userId,
                                   final String currentPassword,
                                   final String password,
                                   final String confirmPassword) {

        if (StringUtils.isBlank(currentPassword)) {
            throw new BadRequestException(CURRENT_PASSWORD_PROPERTY, IS_MISSING_MESSAGE);
        }

        validatePassword(password, confirmPassword);

        final User user = getById(userId);
        final User currentUser = getCurrentUser().orElseThrow(UnauthorizedException::new);

        if (!Objects.equals(currentUser.getId(), user.getId())
            || !this.passwordEncoder.matches(currentPassword, currentUser.getEncryptedPassword())) {
            throw new ForbiddenException();
        }

        currentUser.setEncryptedPassword(this.passwordEncoder.encode(password));

        return getJpaRepository().save(currentUser);
    }

    /**
     * Deletes a user.
     *
     * @param userId Id of the user.
     */
    public void deleteUser(final Long userId) {
        final User user = getById(userId);
        getJpaRepository().delete(user);
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

    /**
     * Validates that password anc password confirmation are not empty and match.
     *
     * @param password        The password.
     * @param confirmPassword The password confirmation.
     */
    private void validatePassword(final String password, final String confirmPassword) {
        if (StringUtils.isBlank(password)) {
            throw new BadRequestException(PASSWORD_PROPERTY, IS_MISSING_MESSAGE);
        }

        if (StringUtils.isBlank(confirmPassword)) {
            throw new BadRequestException(CONFIRM_PASSWORD_PROPERTY, IS_MISSING_MESSAGE);
        }

        if (!StringUtils.equals(password, confirmPassword)) {
            throw new BadRequestException(CONFIRM_PASSWORD_PROPERTY, "must match password");
        }
    }

}
