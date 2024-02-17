package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer bid service tests.
 */
class TransferServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked transfer repository.
     */
    @Mock
    private TransferRepository transferRepository;

    /**
     * Transfer service.
     */
    @InjectMocks
    private TransferService transferService;

    /**
     * Tests TransferService::getByTransferDayId.
     */
    @Test
    void testGetByTransferDayId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String transferDayIdProperty = "transferDayId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferService.getByTransferDayId(null),
                             "TransferService::getByTransferDayId null transferDayId throws");
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter(),
                     "TransferService::getByTransferDayId property equals null transferDayId");

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferService.getByTransferDayId(-1L),
                             "TransferService::getByTransferDayId invalid transferDayId throws");
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter(),
                     "TransferService::getByTransferDayId property equals invalid transferDayId");

        // Success -----------------------------------------------------------------------------------------------------

        final long transferDayId = 1L;
        final List<Transfer> transfers = generateList(Transfer.class);

        when(this.transferRepository.findByTransferDayIdOrderByD11TeamNameAscFeeDesc(eq(transferDayId)))
                .thenReturn(transfers);

        final List<Transfer> result = this.transferService.getByTransferDayId(transferDayId);

        assertEquals(transfers, result, "TransferService::getByTransferDayId result equals");

        verify(this.transferRepository, times(1))
                .findByTransferDayIdOrderByD11TeamNameAscFeeDesc(eq(transferDayId));
    }

    /**
     * Tests TransferService::getByPlayerId.
     */
    @Test
    void testGetByPlayerId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String playerIdProperty = "playerId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferService.getByPlayerId(null),
                        "TransferService::getByPlayerId null playerId throws");
        assertEquals(playerIdProperty, nullTransferDayIdException.getParameter(),
                "TransferService::getByPlayerId property equals null playerId");

        final BadRequestException invalidPlayerIdException =
                assertThrows(BadRequestException.class, () -> this.transferService.getByPlayerId(-1L),
                        "TransferService::getByPlayerId invalid playerId throws");
        assertEquals(playerIdProperty, invalidPlayerIdException.getParameter(),
                "TransferService::getByPlayerId property equals invalid playerId");

        // Success -----------------------------------------------------------------------------------------------------

        final long playerId = 1L;
        final List<Transfer> transfers = generateList(Transfer.class);

        when(this.transferRepository.findByPlayerIdOrderByTransferDayDatetimeDesc(eq(playerId))).thenReturn(transfers);

        final List<Transfer> result = this.transferService.getByPlayerId(playerId);

        assertEquals(transfers, result, "TransferService::getByPlayerId result equals");

        verify(this.transferRepository, times(1))
                .findByPlayerIdOrderByTransferDayDatetimeDesc(eq(playerId));
    }

}
