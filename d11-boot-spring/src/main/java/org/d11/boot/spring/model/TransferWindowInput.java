package org.d11.boot.spring.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.d11.boot.util.Status;

import java.time.LocalDateTime;

/**
 * Record containing transfer window properties that can be input by users.
 *
 * @param transferWindowNumber Transfer window number.
 * @param draft Draft.
 * @param status Status.
 * @param datetime Datetime.
 * @param matchWeekId Match week id.
 */
public record TransferWindowInput(
        @PositiveOrZero int transferWindowNumber,
        boolean draft,
        @NotNull Status status,
        @NotNull LocalDateTime datetime,
        @Positive long matchWeekId
) {
}
