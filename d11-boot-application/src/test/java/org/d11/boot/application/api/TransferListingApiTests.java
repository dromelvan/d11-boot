package org.d11.boot.application.api;

import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.api.service.TransferListingApiService;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.repository.TransferListingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing API tests.
 */
public class TransferListingApiTests extends AbstractRepositoryApiTests<TransferListing, TransferListingRepository, TransferListingApiService> {

    /**
     * Sets up transfer days for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findAll());
        Collections.sort(getEntities());
        assertFalse(getEntities().isEmpty(), "Transfer listings should not be empty.");
    }

    /**
     * Tests the findTransferListingById API operation.
     */
    @Test
    public void findTransferListingById() {
        for(final TransferListing transferListing : getRepository().findAll()) {
            final TransferListingDTO result = getApiService().findTransferListingById(transferListing.getId());
            final TransferListingDTO transferListingDTO = map(transferListing, TransferListingDTO.class);
            assertNotNull(result, "Transfer listing by id should not be null.");
            assertEquals(transferListingDTO, result, "Transfer listing by id should equal TransferListing.");
        }

        assertNull(getApiService().findTransferListingById(-1L), "Transfer day not found should return null.");
        assertBadRequest(get("BAD_TRANSFER_LISTING_REQUEST"));
    }

    /**
     * Tests the findTransferListingByTransferDayId API operation.
     */
    @Test
    public void findTransferDayByTransferWindowId() {
        final Map<TransferDay, List<TransferListing>> transferDayMap = new HashMap<>();
        for(final TransferListing transferListing : getEntities()) {
            final List<TransferListing> transferListings = transferDayMap.computeIfAbsent(transferListing.getTransferDay(), p -> new ArrayList<>());
            transferListings.add(transferListing);
        }

        for(final Map.Entry<TransferDay, List<TransferListing>> entry : transferDayMap.entrySet()) {
            final TransferDay transferDay = entry.getKey();
            final List<TransferListing> transferListings = entry.getValue();

            transferListings.sort(Comparator.comparing(TransferListing::getRanking));

            final List<TransferListingDTO> result = getApiService().findTransferListingByTransferDayId(transferDay.getId(), 0);

            assertNotNull(result, "Transfer listings by transfer day id should not be null.");
            assertEquals(map(transferListings, TransferListingDTO.class), result,
                    "Transfer listings by transfer day id should equal transfer listings.");
        }

        assertTrue(getApiService().findTransferListingByTransferDayId(-1L, 1).isEmpty(),
                "Transfer listings by transfer day id not found should be empty.");
    }

}
