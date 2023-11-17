package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.TransferListingApi;
import org.d11.boot.api.v2.model.TransferListingDTO;
import org.d11.boot.api.v2.model.TransferListingsResponseBodyDTO;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing controller tests.
 */
class TransferListingControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Tests TransferListingControllerV2::getTransferListingsByTransferDayId.
     */
    @Test
    @Transactional
    void testGetTransferListingsByTransferDayId() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferListingApi.getTransferListingsByTransferDayId(null, 1),
                     "TransferListingControllerV2::getTransferListingsByTransferDayId transferDayId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> transferListingApi.getTransferListingsByTransferDayId(1L, null),
                     "TransferListingControllerV2::getTransferListingsByTransferDayId page null throws");

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertTrue(transferDays.size() > 1,
                   "TransferListingControllerV2::getTransferListingsByTransferDayId transferDays size > 0");

        int pendingCount = 0;
        int notPendingCount = 0;

        for (final TransferDay transferDay : transferDays) {
            final TransferListingsResponseBodyDTO transferListingsResponseBodyDTO =
                    transferListingApi.getTransferListingsByTransferDayId(transferDay.getId(), 0);
            assertNotNull(transferListingsResponseBodyDTO,
                          "TransferListingControllerV2::getTransferListingsByTransferDayId response not null");

            final List<TransferListingDTO> result = transferListingsResponseBodyDTO.getTransferListings();

            assertNotNull(result, "TransferListingControllerV2::getTransferListingsByTransferDayId not null ");

            if (Status.PENDING.equals(transferDay.getStatus())) {
                assertTrue(result.isEmpty(),
                           "TransferListingControllerV2::getTransferListingsByTransferDayId pending empty");
                assertFalse(transferDay.getTransferListings().isEmpty(),
                           "TransferListingControllerV2::getTransferListingsByTransferDayId test data not empty");
                ++pendingCount;
            } else {
                assertFalse(result.isEmpty(), "TransferListingControllerV2::getTransferListingsByTransferDayId empty");
                assertEquals(map(transferDay.getTransferListings(), TransferListingDTO.class), result,
                             "TransferListingControllerV2::getTransferListingsByTransferDayId equals");
                ++notPendingCount;
            }
        }

        // Make sure that we have both pending and not pending test transfer days
        assertTrue(pendingCount > 0 && notPendingCount > 0,
                   "TransferListingControllerV2::getTransferListingsByTransferDayId pending and not pending count > 0");
    }

}
