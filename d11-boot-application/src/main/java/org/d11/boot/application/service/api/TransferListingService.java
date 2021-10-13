package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.repository.TransferListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides transfer day services.
 */
@Service
public class TransferListingService extends ApiRepositoryService<TransferListing, TransferListingDTO, TransferListingRepository> {

    /**
     * Page size for when getting transfer listings for a transfer day.
     */
    public static final int PAGE_SIZE = 25;

    /**
     * Creates a new service.
     *
     * @param transferListingRepository The repository this service will use.
     */
    @Autowired
    public TransferListingService(final TransferListingRepository transferListingRepository) {
        super(transferListingRepository);
    }

    /**
     * Gets transfer listings for a specific transfer day.
     *
     * @param transferDayId Id for the transfer day for which transfer listings will be looked up.
     * @param page Page number (25 per page) for the search result page that will be returned.
     * @return Transfer listings for the transfer day, in pages of size 25.
     */
    public List<TransferListingDTO> findByTransferDayId(final long transferDayId, final int page) {
        final Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("ranking"));
        final List<TransferListing> transferListings = getJpaRepository().findByTransferDayIdOrderByRanking(transferDayId, pageable);
        return map(transferListings);
    }

}
