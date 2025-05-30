package org.d11.boot.spring.model;

import jakarta.validation.constraints.NotNull;
import org.d11.boot.util.Status;

/**
 * Record containing transfer day status properties that can be input by users.
 *
 * @param status Status.
 * @param process Datetime.
 */
public record TransferDayStatusInput(
        @NotNull Status status,
        @NotNull boolean process
) {
}
