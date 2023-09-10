package org.d11.boot.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A refresh token that can be used one single time to re-authorize a user.
 */
@Getter
@Entity
@EqualsAndHashCode
public class RefreshToken extends D11Entity {

    /**
     * The name of the cookie used to hold the refresh token.
     */
    public static final String COOKIE_NAME = "refreshToken";

    /**
     * The UUID that is the actual token.
     */
    @NotNull
    private final UUID uuid = UUID.randomUUID();

    /**
     * The time the token expires and can no longer be used to re-authorize a user. If this value is null then the token
     * will not expire.
     */
    private final LocalDateTime expiresAt;

    /**
     * The user the token is valid for.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "application_user_id", nullable = false)
    @ToString.Exclude
    private final User user;

    /**
     * Creates a new token. This token will be invalid but for some reason it won't compile without this constructor.
     */
    @SuppressWarnings("PMD.NullAssignment")
    protected RefreshToken() {
        this.user = null;
        this.expiresAt = null;
    }

    /**
     * Creates a new token for a user.
     *
     * @param user The token user.
     */
    @SuppressWarnings("PMD.NullAssignment")
    public RefreshToken(final User user) {
        this.user = user;
        this.expiresAt = null;
    }

    /**
     * Creates a new token for a user with an expires at time.
     *
     * @param user      The token user.
     * @param expiresAt The time the token will expire.
     */
    public RefreshToken(final User user, final LocalDateTime expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }

}
