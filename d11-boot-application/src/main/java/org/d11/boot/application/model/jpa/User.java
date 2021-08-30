package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

}
