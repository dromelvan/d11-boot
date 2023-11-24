package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferBidRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Transfer bid service.
 */
@Service
public class TransferBidService extends RepositoryService<TransferBid, TransferBidRepository> {

    /**
     * Repository for looking up current transfer day.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Creates a new transfer bid service.
     *
     * @param transferBidRepository The transfer bid repository the service will use.
     * @param transferDayRepository The transfer day repository the service will use.
     */
    @Autowired
    public TransferBidService(final TransferBidRepository transferBidRepository,
                              final TransferDayRepository transferDayRepository) {
        super(TransferBid.class, transferBidRepository);
        this.transferDayRepository = transferDayRepository;
    }

    /**
     * Get transfer bids by transfer day id ordered by ranking.
     *
     * @param transferDayId The transfer day id.
     * @return Transfer bids by transfer day id ordered by ranking in pages of size 25.
     */
    public List<TransferBid> getByTransferDayId(final Long transferDayId) {
        if (transferDayId == null || transferDayId <= 0) {
            throw new BadRequestException("transferDayId", "must be positive");
        }

        final List<TransferBid> transferBids = new ArrayList<>();

        final Optional<TransferDay> optional = this.transferDayRepository.findById(transferDayId);

        optional.ifPresent(transferDay -> {
            if (Status.FINISHED.equals(transferDay.getStatus())) {
                transferBids.addAll(getJpaRepository()
                        .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDayId));
            }
        });

        return transferBids;
    }

}
