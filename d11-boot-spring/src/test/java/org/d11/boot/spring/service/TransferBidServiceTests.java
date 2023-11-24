package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferBidRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer bid service tests.
 */
class TransferBidServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked transfer bid repository.
     */
    @Mock
    private TransferBidRepository transferBidRepository;

    /**
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Transfer listing service.
     */
    @InjectMocks
    private TransferBidService transferBidService;

    /**
     * Tests TransferBidService::getByTransferDayId.
     */
    @Test
    void testGetByTransferDayId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String transferDayIdProperty = "transferDayId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferBidService.getByTransferDayId(null),
                             "TransferBidService::getByTransferDayId null transferDayId throws");
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter(),
                     "TransferBidService::getByTransferDayId property equals null transferDayId");

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferBidService.getByTransferDayId(-1L),
                             "TransferBidService::getByTransferDayId invalid transferDayId throws");
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter(),
                     "TransferBidService::getByTransferDayId property equals invalid transferDayId");

        // Success -----------------------------------------------------------------------------------------------------

        final TransferDay transferDay = generate(TransferDay.class);

        final List<TransferBid> transferBids = generateList(TransferBid.class);

        when(this.transferDayRepository.findById(eq(transferDay.getId() + 1))).thenReturn(Optional.empty());

        assertTrue(this.transferBidService.getByTransferDayId(transferDay.getId() + 1).isEmpty(),
                   "TransferBidService::getByTransferDayId invalid transferDayId isEmpty");

        transferDay.setStatus(Status.PENDING);
        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));

        assertTrue(this.transferBidService.getByTransferDayId(transferDay.getId()).isEmpty(),
                   "TransferBidService::getByTransferDayId transfer day pending isEmpty");

        transferDay.setStatus(Status.ACTIVE);
        assertTrue(this.transferBidService.getByTransferDayId(transferDay.getId()).isEmpty(),
                   "TransferBidService::getByTransferDayId transfer day active isEmpty");

        transferDay.setStatus(Status.FINISHED);
        when(this.transferBidRepository
                     .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(
                             eq(transferDay.getId()))
                     )
                .thenReturn(transferBids);

        final List<TransferBid> result = this.transferBidService.getByTransferDayId(transferDay.getId());

        assertEquals(transferBids, result, "TransferBidService::getByTransferDayId result equals");

        verify(this.transferBidRepository, times(1))
                .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(eq(transferDay.getId()));
    }

}
