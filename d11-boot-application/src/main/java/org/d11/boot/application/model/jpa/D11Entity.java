package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.d11.boot.application.model.jpa.validation.ValidatedD11Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Base class for database entities.
 */
@Data
@MappedSuperclass
@ValidatedD11Entity
public class D11Entity {

    /**
     * Entity validator.
     */
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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
     * @return Set of constraint violations. If the entity is valid, this set is empty.
     */
    public Set<ConstraintViolation<D11Entity>> validate() {
        return D11Entity.validator.validate(this);
    }

    /**
     * Validates the entity.
     *
     * @return True if the entity is valid, false if it is not.
     */
    public boolean isValid() {
        return validate().isEmpty();
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

}
