package org.d11.boot.spring.security;

import java.time.LocalDateTime;

/**
 * Record that holds the result of a JWT build.
 *
 * @param jwt The created JWT.
 * @param expiresAt The time the JWT expires.
 */
public record JwtBuildResult(String jwt, LocalDateTime expiresAt) {

}
