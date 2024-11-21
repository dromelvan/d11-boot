package org.d11.boot.spring.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.d11.boot.util.Status;

import java.time.LocalDateTime;

/**
 * Record containing transfer day properties that can be input by users.
 *
 * @param transferDayNumber Transfer day number.
 * @param status Status.
 * @param datetime Datetime.
 */
public record TransferDayInput(
        @Positive int transferDayNumber,
        @NotNull Status status,
        @NotNull LocalDateTime datetime
) {
}
