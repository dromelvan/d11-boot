package org.d11.boot.spring.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

/**
 * Record containing player properties that can be input by users.
 *
 * @param statSourceId    Stat source id.
 * @param premierLeagueId Premier league id.
 * @param firstName       First name.
 * @param lastName        Last name.
 * @param fullName        Full name.
 * @param dateOfBirth     Date of birth.
 * @param height          Height.
 * @param countryId       Country id.
 * @param verified        Verified.
 */
public record PlayerInput(
        @PositiveOrZero int statSourceId,
        @PositiveOrZero int premierLeagueId,
        @NotNull String firstName,
        @NotEmpty String lastName,
        String fullName,
        LocalDate dateOfBirth,
        @PositiveOrZero int height,
        @PositiveOrZero long countryId,
        boolean verified
) {
}
