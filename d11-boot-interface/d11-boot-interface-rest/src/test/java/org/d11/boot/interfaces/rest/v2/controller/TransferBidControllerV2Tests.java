package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.TransferBidApi;
import org.d11.boot.api.v2.model.TransferBidDTO;
import org.d11.boot.api.v2.model.TransferBidsResponseBodyDTO;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferBidRepository;
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
 * Transfer bid controller tests.
 */
class TransferBidControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Transfer bid repository.
     */
    @Autowired
    private TransferBidRepository transferBidRepository;

    /**
     * Tests TransferBidControllerV2::getTransferBidsByTransferDayId.
     */
    @Test
    @Transactional
    void testGetTransferBidsByTransferDayId() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferBidApi.getTransferBidsByTransferDayId((Long) null),
                     "TransferBidControllerV2::getTransferBidsByTransferDayId transferDayId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> transferBidApi.getTransferBidsByTransferDayId(-1L),
                     "TransferBidControllerV2::getTransferBidsByTransferDayId transferDayId negative throws");

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertTrue(transferDays.size() > 1,
                   "TransferBidControllerV2::getTransferBidsByTransferDayId transferDays size > 0");

        int finishedCount = 0;
        int notFinishedCount = 0;

        for (final TransferDay transferDay : transferDays) {
            final TransferBidsResponseBodyDTO transferBidsResponseBodyDTO =
                    transferBidApi.getTransferBidsByTransferDayId(transferDay.getId());
            assertNotNull(transferBidsResponseBodyDTO,
                          "TransferBidControllerV2::getTransferBidsByTransferDayId response not null");

            final List<TransferBidDTO> result = transferBidsResponseBodyDTO.getTransferBids();

            assertNotNull(result, "TransferBidControllerV2::getTransferBidsByTransferDayId not null ");

            if (Status.FINISHED.equals(transferDay.getStatus())) {
                assertFalse(result.isEmpty(), "TransferBidControllerV2::getTransferBidsByTransferDayId empty");

                final List<TransferBid> transferBids = this.transferBidRepository
                        .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDay.getId());

                assertEquals(map(transferBids, TransferBidDTO.class), result,
                             "TransferBidControllerV2::getTransferBidsByTransferDayId equals");
                ++finishedCount;
            } else {
                assertTrue(result.isEmpty(),
                           "TransferBidControllerV2::getTransferBidsByTransferDayId pending empty");
                if (!Status.PENDING.equals(transferDay.getStatus())) {
                    assertFalse(transferDay.getTransferBids().isEmpty(),
                                "TransferBidControllerV2::getTransferBidsByTransferDayId test data not empty");
                }
                ++notFinishedCount;
            }
        }

        // Make sure that we have both finished and not finished test transfer days
        assertTrue(finishedCount > 0 && notFinishedCount > 0,
                   "TransferBidControllerV2::getTransferBidsByTransferDayId active and not active count > 0");
    }

}
