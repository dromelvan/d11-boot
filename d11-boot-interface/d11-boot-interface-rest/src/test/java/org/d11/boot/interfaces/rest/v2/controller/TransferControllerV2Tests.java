package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.TransferApi;
import org.d11.boot.api.v2.model.TransferDTO;
import org.d11.boot.api.v2.model.TransfersResponseBodyDTO;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer controller tests.
 */
class TransferControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Transfer repository.
     */
    @Autowired
    private TransferRepository transferRepository;

    /**
     * Tests TransferControllerV2::getTransfersByTransferDayId.
     */
    @Test
    @Transactional
    void testGetTransferBidsByTransferDayId() {
        final TransferApi transferApi = getApi(TransferApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferApi.getTransfersByTransferDayId((Long) null),
                     "TransferControllerV2::getTransfersByTransferDayId transferDayId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> transferApi.getTransfersByTransferDayId(-1L),
                     "TransferControllerV2::getTransfersByTransferDayId transferDayId negative throws");

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertTrue(transferDays.size() > 1,
                   "TransferControllerV2::getTransfersByTransferDayId transferDays size > 0");

        for (final TransferDay transferDay : transferDays) {
            final TransfersResponseBodyDTO transfersByTransferDayId =
                    transferApi.getTransfersByTransferDayId(transferDay.getId());
            assertNotNull(transfersByTransferDayId,
                          "TransferControllerV2::getTransfersByTransferDayId response not null");

            final List<TransferDTO> result = transfersByTransferDayId.getTransfers();

            assertNotNull(result, "TransferControllerV2::getTransfersByTransferDayId not null ");

            final List<Transfer> transfers = this.transferRepository
                    .findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDay.getId());

            assertEquals(map(transfers, TransferDTO.class), result,
                         "TransferControllerV2::getTransfersByTransferDayId equals");
        }
    }

}
