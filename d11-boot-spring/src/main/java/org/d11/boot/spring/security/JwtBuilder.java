package org.d11.boot.spring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * Generates and signs JWTs using Auth0. We're basically using this instead of an OAuth2 authorization server.
 */
@Component
public class JwtBuilder {

    /**
     * The name of the JWT claim that contains the username.
     */
    public static final String USERNAME_CLAIM = "username";

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
     * @param expiresAt JWT expiration time.
     * @return A generated JWT as a String.
     */
    public String build(final String username, final LocalDateTime expiresAt) {
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim(USERNAME_CLAIM, username)
                .withExpiresAt(Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.RSA256(this.rsaPublicKey, this.rsaPrivateKey));
    }

    /**
     * Decodes a JWT.
     *
     * @param jwt The jwt that will be decoded.
     * @return Decoded JWT.
     */
    public DecodedJWT decode(final String jwt) {
        final JWTVerifier verifier = JWT.require(Algorithm.RSA256(this.rsaPublicKey, this.rsaPrivateKey)).build();
        return verifier.verify(jwt);
    }

}
