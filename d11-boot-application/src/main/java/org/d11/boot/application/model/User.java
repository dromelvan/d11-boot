package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

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
     * The D11 team that has this user registered as its owner.
     */
    @OneToOne(mappedBy = "owner")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team ownedD11Team;

    /**
     * The D11 team that has this user registered as its co-owner.
     */
    @OneToOne(mappedBy = "coOwner")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team coOwnedD11Team;

    /**
     * Gets the D11 team currently owned or co-owned by the user.
     *
     * @return Optional of a D11 team if the user owns or co-owns one, empty optional if not.
     */
    @SuppressWarnings("PMD.ConfusingTernary")
    public Optional<D11Team> getD11Team() {
        if(this.ownedD11Team != null) {
            return Optional.of(this.ownedD11Team);
        } else if(this.coOwnedD11Team != null) {
            return Optional.of(this.coOwnedD11Team);
        } else {
            return Optional.empty();
        }
    }

}
