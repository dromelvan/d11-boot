package org.d11.boot.application.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.UUID;

/**
 * Generates and signs JWTs using Auth0.
 */
@Component
public class JwtBuilder {

    /**
     * The RSA private key that will be used to sign the JWTs.
     */
    private final RSAPrivateKey rsaPrivateKey;
    /**
     * The RSA public key that will be used to sign the JWTs.
     */
    private final RSAPublicKey rsaPublicKey;

    /**
     * Creates a new JWT builder.
     *
     * @param rsaPrivateKey The RSA private key that will be used to sign the JWT.
     * @param rsaPublicKey  The RSA public key that will be used to sign the JWTs.
     */
    public JwtBuilder(final RSAPrivateKey rsaPrivateKey, final RSAPublicKey rsaPublicKey) {
        this.rsaPrivateKey = rsaPrivateKey;
        this.rsaPublicKey = rsaPublicKey;
    }

    /**
     * Builds and signs a JWT that contains jti (JWT id), exp (expiration time) and username claims.
     *
     * @param username Username that will be included in the JWT as a 'username' claim.
     * @return A generated JWT as a String.
     */
    public String build(final String username) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("username", username)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.RSA256(this.rsaPublicKey, this.rsaPrivateKey));
    }

}
