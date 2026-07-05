package org.d11.boot.spring.model;

import org.d11.boot.spring.model.validation.TransferFee;

/**
 * Record containing transfer properties that can be updated by users.
 *
 * @param fee       Transfer fee.
 * @param d11TeamId D11 team id.
 */
public record UpdateTransferInput(@TransferFee int fee,
                                  long d11TeamId) {
}
