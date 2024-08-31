package org.d11.boot.spring.model;

import jakarta.validation.constraints.Positive;

/**
 * Record containing player season stat properties that can be input by users when creating a new player season stat.
 *
 * @param playerId   Player id.
 * @param teamId     Team id.
 * @param positionId Position id.
 */
public record CreatePlayerSeasonStatInput(
        @Positive long playerId,
        @Positive long teamId,
        @Positive long positionId
) {
}
