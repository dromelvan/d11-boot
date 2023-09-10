package org.d11.boot.spring.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.d11.boot.spring.model.converter.UuidConverter;

import java.util.UUID;

/**
 * A user than can log in and administer a D11 team, or the whole site if they're an administrator.
 */
@Data
@Entity
@Table(name = "application_user")
public class User extends D11Entity {

    /**
     * User name.
     */
    @NotEmpty
    private String name;

    /**
     * User email.
     */
    @Email
    @NotEmpty
    private String email;

    /**
     * Encrypted password.
     */
    @NotEmpty
    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    /**
     * Is the user an administrator or not.
     */
    private boolean administrator;

    /**
     * Token that must be provided and match in order to reset the user password.
     */
    @Convert(converter = UuidConverter.class)
    private UUID resetPasswordToken;

}
