package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Transfer service.
 */
@Service
public class TransferService extends RepositoryService<Transfer, TransferRepository> {

    /**
     * Creates a new transfer service.
     *
     * @param transferRepository The transfer repository the service will use.
     */
    @Autowired
    public TransferService(final TransferRepository transferRepository) {
        super(Transfer.class, transferRepository);
    }

    /**
     * Get transfers by transfer day id ordered by D11 team name and fee, descending.
     *
     * @param transferDayId The transfer day id.
     * @return Transfers by transfer day id ordered by D11 team name and fee, descending.
     */
    public List<Transfer> getByTransferDayId(final Long transferDayId) {
        if (transferDayId == null || transferDayId <= 0) {
            throw new BadRequestException("transferDayId", "must be positive");
        }

        return getJpaRepository().findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDayId);
    }

}
