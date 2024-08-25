package org.d11.boot.spring.service;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.D11EntityRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.util.exception.ValidationError;
import org.d11.boot.util.mapper.MapperProvider;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Base class for D11 Boot services.
 */
public class D11BootService extends MapperProvider implements ApplicationContextAware {

    /**
     * Service mapper.
     */
    private static final ServiceMapper MAPPER = Mappers.getMapper(ServiceMapper.class);

    /**
     * Entity validator.
     */
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Application context used to provide repositories.
     */
    private ApplicationContext applicationContext;

    /**
     * Sets the application context used to provide repositories.
     *
     * @param applicationContext Application context used to provide repositories.
     */
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Gets a D11 entity repository of a specific class.
     *
     * @param repositoryClass The repository class we want an instance of.
     * @param <T>             The type of repository class we want an instance of.
     * @return Instance of the provided repository class.
     */
    protected <T extends D11EntityRepository<?>> T getRepository(final Class<T> repositoryClass) {
        return this.applicationContext.getBean(repositoryClass);
    }

    /**
     * Gets the user that is authenticated in the current security context. This method is intended to be used from
     * methods protected by @RolesAllowed where we can be sure there is a JWT authentication in the context.
     *
     * @return The user authenticated in the current security context.
     */
    protected Optional<User> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.getPrincipal() instanceof Jwt jwt) {
            final UserRepository userRepository = getRepository(UserRepository.class);
            return userRepository.findByEmail(jwt.getClaimAsString(JwtBuilder.USERNAME_CLAIM));
        }
        return Optional.empty();
    }

    /**
     * Gets the service mapper.
     *
     * @return The service mapper.
     */
    protected ServiceMapper getServiceMapper() {
        return MAPPER;
    }

    /**
     * Gets a list of validation errors.
     *
     * @param object The object that will be validated.
     * @return List of validation errors. If the entity is valid, this list is empty.
     */
    protected List<ValidationError> validate(final Object object) {
        return VALIDATOR.validate(object).stream()
                .map(error -> new ValidationError(error.getPropertyPath().toString(),
                                                  error.getMessage()))
                .sorted(Comparator.comparing(ValidationError::property))
                .toList();
    }

}
