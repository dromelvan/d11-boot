package org.d11.boot.spring.model;

import jakarta.validation.constraints.Positive;
import org.d11.boot.spring.model.validation.TransferFee;

/**
 * Record containing transfer properties that can be input by users.
 *
 * @param fee           Transfer fee.
 * @param transferDayId Transfer day id.
 * @param playerId      Player id.
 * @param d11TeamId     D11 team id.
 */
public record TransferInput(@TransferFee int fee,
                            @Positive long transferDayId,
                            @Positive long playerId,
                            @Positive long d11TeamId) {
}
