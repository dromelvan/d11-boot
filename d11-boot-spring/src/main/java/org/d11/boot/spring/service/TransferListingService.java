package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferListingRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Transfer listing service.
 */
@Service
public class TransferListingService extends RepositoryService<TransferListing, TransferListingRepository> {

    /**
     * Transfer listing list page size.
     */
    public static final int PAGE_SIZE = 25;

    /**
     * Repository for looking up current transfer day.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Creates a new transfer listing service.
     *
     * @param transferListingRepository The transfer listing repository the service will use.
     * @param transferDayRepository     The transfer day repository the service will use.
     */
    @Autowired
    public TransferListingService(final TransferListingRepository transferListingRepository,
                                  final TransferDayRepository transferDayRepository) {
        super(TransferListing.class, transferListingRepository);
        this.transferDayRepository = transferDayRepository;
    }

    /**
     * Get transfer listings by transfer day id ordered by ranking.
     *
     * @param transferDayId The transfer day id.
     * @param page          Page number (25 per page) for the search result page that will be returned.
     * @return Transfer listings by transfer day id ordered by ranking in pages of size 25.
     */
    public List<TransferListing> getByTransferDayId(final Long transferDayId, final int page) {
        if (transferDayId == null || transferDayId <= 0) {
            throw new BadRequestException("transferDayId", "must be positive");
        }

        if (page < 0) {
            throw new BadRequestException("page", "must be non-negative");
        }

        final TransferDay transferDay = this.transferDayRepository.findById(transferDayId).orElse(null);

        if (transferDay != null && Status.PENDING.equals(transferDay.getStatus())) {
            return new ArrayList<>();
        }

        final Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("ranking"));
        return getJpaRepository().findByTransferDayIdOrderByRanking(transferDayId, pageable);
    }

}
