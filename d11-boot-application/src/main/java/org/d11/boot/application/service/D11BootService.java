package org.d11.boot.application.service;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.model.D11Entity;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.application.repository.D11EntityRepository;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.application.util.MappingProvider;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for services.
 */
public class D11BootService extends MappingProvider implements ApplicationContextAware {

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
     * Gets the current season.
     *
     * @return The current season.
     */
    protected Season getCurrentSeason() {
        return getRepository(SeasonRepository.class).findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);
    }

    /**
     * Gets the dummy D11 team.
     *
     * @return The dummy D11 team.
     */
    protected D11Team getDummyD11Team() {
        return getRepository(D11TeamRepository.class).findById(D11Team.DUMMY_D11_TEAM_ID).orElseThrow(NotFoundException::new);
    }

    /**
     * Gets the dummy team.
     *
     * @return The dummy team.
     */
    protected Team getDummyTeam() {
        return getRepository(TeamRepository.class).findById(Team.DUMMY_TEAM_ID).orElseThrow(NotFoundException::new);
    }

    /**
     * Gets the dummy country.
     *
     * @return The dummy country.
     */
    protected Country getDummyCountry() {
        return getRepository(CountryRepository.class).findById(Country.DUMMY_COUNTRY_ID).orElseThrow(NotFoundException::new);
    }

    /**
     * Gets a D11 entity repository of a specific class.
     *
     * @param repositoryClass The repository class we want an instance of.
     * @param <T> The type of repository class we want an instance of.
     * @return Instance of the provided repository class.
     */
    protected <T extends D11EntityRepository<?>> T getRepository(final Class<T> repositoryClass) {
        return this.applicationContext.getBean(repositoryClass);
    }

    /**
     * Validates provided entities.
     *
     * @param entities The entities that will be provided.
     * @return List of validation error messages.
     */
    protected List<String> getValidationErrors(final D11Entity... entities) {
        final List<String> errors = new ArrayList<>();
        for(final D11Entity entity : entities) {
            for(final ConstraintViolation<D11Entity> constraintViolation : entity.validate()) {
                errors.add(StringUtils.capitalize(constraintViolation.getPropertyPath().toString()) + " " + constraintViolation.getMessage());
            }
        }
        return errors;
    }

    /**
     * Performs a rollback in methods annotated with @Transactional without throwing an exception.
     */
    protected void rollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

}
