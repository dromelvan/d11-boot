package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TransferWindowDTO;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.TransferWindowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides transfer window services.
 */
@Service
public class TransferWindowService extends ApiRepositoryService<TransferWindow, TransferWindowDTO, TransferWindowRepository> {

    /**
     * Creates a new service.
     *
     * @param transferWindowRepository The repository this service will use.
     */
    @Autowired
    public TransferWindowService(final TransferWindowRepository transferWindowRepository) {
        super(transferWindowRepository);
    }

    /**
     * Gets the current transfer window.
     *
     * @return The current transfer window DTO.
     */
    public TransferWindowDTO findCurrentTransferWindow() {
        final Optional<TransferWindow> optional = getJpaRepository().findFirstByOrderByDatetimeDesc();
        return mapIfFound(optional.orElse(null));
    }

    /**
     * Gets transfer windows for a specific season.
     *
     * @param seasonId Id for the season for which transfer windows will be looked up.
     * @return Transfer windows for the season.
     */
    public List<TransferWindowDTO> findBySeasonId(final long seasonId) {
        final List<TransferWindow> transferWindows = getJpaRepository().findByMatchWeekSeasonIdOrderByDatetimeDesc(seasonId);
        return map(transferWindows);
    }

}
