package org.d11.boot.spring.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.d11.boot.spring.model.validation.ValidatedD11Entity;
import org.d11.boot.util.exception.ValidationError;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Base class for database entities.
 */
@Data
@MappedSuperclass
@ValidatedD11Entity
public class D11Entity extends D11Model {

    /**
     * Entity validator.
     */
    private static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Entity creation timestamp.
     */
    @NotNull
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Latest update timestamp.
     */
    @NotNull
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt = this.createdAt;

    /**
     * Validates the entity.
     *
     * @return True if the entity is valid, false if it is not.
     */
    public boolean isValid() {
        return VALIDATOR.validate(this).isEmpty();
    }

    /**
     * Gets a list of validation errors.
     *
     * @return List of validation errors. If the entity is valid, this list is empty.
     */
    public List<ValidationError> getValidationErrors() {
        return VALIDATOR.validate(this).stream()
                .map(error -> new ValidationError(error.getPropertyPath().toString(),
                                                  error.getMessage()))
                .sorted(Comparator.comparing(ValidationError::property))
                .toList();
    }

    /**
     * Updates the creation and updated timestamps before the entity is persisted.
     */
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
            this.updatedAt = this.createdAt;
        }
    }

    /**
     * Updates the updated timestamp before the entity is updated.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(final Object object) {
        return object != null
                // Hibernate creates proxy objects so can't use equals.
                && getClass().isAssignableFrom(object.getClass())
                && ((D11Entity) object).getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, getClass());
    }

}
