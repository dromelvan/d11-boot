package org.d11.boot.application.service;

import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.repository.TransferDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides transfer day services.
 */
@Service
public class TransferDayService extends AbstractRepositoryService<TransferDay, TransferDayDTO, TransferDayRepository> {

    /**
     * Creates a new service.
     *
     * @param transferDayRepository The repository this service will use.
     */
    @Autowired
    public TransferDayService(final TransferDayRepository transferDayRepository) {
        super(transferDayRepository);
    }

    /**
     * Gets the current transfer day.
     *
     * @return The current transfer day DTO.
     */
    public TransferDayDTO findCurrentTransferDay() {
        final Optional<TransferDay> optional = getJpaRepository().findFirstByOrderByDatetimeDesc();
        return mapIfFound(optional.orElse(null));
    }

    /**
     * Gets transfer days for a specific transfer window.
     *
     * @param transferWindowId Id for the transfer window for which transfer days will be looked up.
     * @return Transfer days for the transfer window.
     */
    public List<TransferDayDTO> findByTransferWindowId(final long transferWindowId) {
        final List<TransferDay> transferDays = getJpaRepository().findByTransferWindowIdOrderByDatetimeDesc(transferWindowId);
        return map(transferDays);
    }

}
