package org.d11.boot.spring.model;

import jakarta.validation.constraints.Positive;

/**
 * Record containing player season stat properties that can be input by users when updating a new player season stat.
 *
 * @param playerId   Player id.
 * @param teamId     Team id.
 * @param d11TeamId  D11 team id.
 * @param positionId Position id.
 */
public record UpdatePlayerSeasonStatInput(
        @Positive long playerId,
        @Positive long teamId,
        @Positive long d11TeamId,
        @Positive long positionId
) {
}
