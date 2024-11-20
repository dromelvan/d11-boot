package org.d11.boot.processing.service;

import org.d11.boot.spring.model.TransferWindow;

import java.time.LocalDateTime;

/**
 * Process transfer window service.
 */
public interface ProcessTransferWindowService {

    /**
     * Creates a new transfer window.
     *
     * @param datetime Transfer window transfer listing deadline.
     * @param transferDayDelay Number of days after transfer listing deadline the first transfer day will take place.
     * @return New transfer window.
     */
    TransferWindow createTransferWindow(LocalDateTime datetime, int transferDayDelay);

}
