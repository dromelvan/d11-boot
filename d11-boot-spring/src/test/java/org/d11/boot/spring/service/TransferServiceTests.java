package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferInput;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Mocked player repository.
     */
    @Mock
    private PlayerRepository playerRepository;

    /**
     * Mocked D11 team repository.
     */
    @Mock
    private D11TeamRepository d11TeamRepository;

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
                assertThrows(BadRequestException.class, () -> this.transferService.getByTransferDayId(null));
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter());

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferService.getByTransferDayId(-1L));
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long transferDayId = 1L;
        final List<Transfer> transfers = generateList(Transfer.class);

        when(this.transferRepository.findByTransferDayIdOrderByD11TeamNameAscFeeDesc(eq(transferDayId)))
                .thenReturn(transfers);

        final List<Transfer> result = this.transferService.getByTransferDayId(transferDayId);

        assertEquals(transfers, result);

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
                assertThrows(BadRequestException.class, () -> this.transferService.getByPlayerId(null));
        assertEquals(playerIdProperty, nullTransferDayIdException.getParameter());

        final BadRequestException invalidPlayerIdException =
                assertThrows(BadRequestException.class, () -> this.transferService.getByPlayerId(-1L));
        assertEquals(playerIdProperty, invalidPlayerIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long playerId = 1L;
        final List<Transfer> transfers = generateList(Transfer.class);

        when(this.transferRepository.findByPlayerIdOrderByTransferDayDatetimeDesc(eq(playerId))).thenReturn(transfers);

        final List<Transfer> result = this.transferService.getByPlayerId(playerId);

        assertEquals(transfers, result);

        verify(this.transferRepository, times(1))
                .findByPlayerIdOrderByTransferDayDatetimeDesc(eq(playerId));
    }

    // createTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferService::createTransfer with invalid fee.
     */
    @Test
    void testCreateTransferInvalidFee() {
        final String feeProperty = "fee";

        final BadRequestException negativeFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.createTransfer(new TransferInput(-5, 0, 0, 0)));

        assertEquals(feeProperty, negativeFeeException.getParameter());
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), negativeFeeException.getMessage());

        final BadRequestException invalidFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.createTransfer(new TransferInput(1, 0, 0, 0)));

        assertEquals(feeProperty, invalidFeeException.getParameter());
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), invalidFeeException.getMessage());
    }

    /**
     * Tests TransferService::createTransfer with transfer day not found.
     */
    @Test
    void testCreateTransferTransferDayNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId()))).thenReturn(Optional.empty());

        final NotFoundException transferDayException =
                assertThrows(NotFoundException.class, () -> this.transferService.createTransfer(transferInput));

        assertEquals(TransferDay.class.getSimpleName(), transferDayException.getResource());
        assertEquals(transferInput.transferDayId(), transferDayException.getId());
    }

    /**
     * Tests TransferService::createTransfer with player not found.
     */
    @Test
    void testCreateTransferPlayerNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId())))
                .thenReturn(Optional.of(generate(TransferDay.class)));
        when(this.playerRepository.findById(eq(transferInput.playerId())))
                .thenReturn(Optional.empty());

        final NotFoundException transferDayException =
                assertThrows(NotFoundException.class, () -> this.transferService.createTransfer(transferInput));

        assertEquals(Player.class.getSimpleName(), transferDayException.getResource());
        assertEquals(transferInput.playerId(), transferDayException.getId());
    }

    /**
     * Tests TransferService::createTransfer with D11 team not found.
     */
    @Test
    void testCreateTransferD11TeamNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId())))
                .thenReturn(Optional.of(generate(TransferDay.class)));
        when(this.playerRepository.findById(eq(transferInput.playerId())))
                .thenReturn(Optional.of(generate(Player.class)));
        when(this.d11TeamRepository.findById(eq(transferInput.d11TeamId())))
                .thenReturn(Optional.empty());

        final NotFoundException transferDayException =
                assertThrows(NotFoundException.class, () -> this.transferService.createTransfer(transferInput));

        assertEquals(D11Team.class.getSimpleName(), transferDayException.getResource());
        assertEquals(transferInput.d11TeamId(), transferDayException.getId());
    }

    /**
     * Tests TransferService::createTransfer.
     */
    @Test
    void testCreateTransfer() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        final TransferDay transferDay = generate(TransferDay.class);
        final Player player = generate(Player.class);
        final D11Team d11Team = generate(D11Team.class);

        when(this.transferDayRepository.findById(eq(transferInput.transferDayId())))
                .thenReturn(Optional.of(transferDay));
        when(this.playerRepository.findById(eq(transferInput.playerId())))
                .thenReturn(Optional.of(player));
        when(this.d11TeamRepository.findById(eq(transferInput.d11TeamId())))
                .thenReturn(Optional.of(d11Team));
        when(this.transferRepository.save(any(Transfer.class))).then(AdditionalAnswers.returnsFirstArg());

        final Transfer transfer = this.transferService.createTransfer(transferInput);

        assertNotNull(transfer);
        assertEquals(transferInput.fee(), transfer.getFee());
        assertEquals(transferDay, transfer.getTransferDay());
        assertEquals(player, transfer.getPlayer());
        assertEquals(d11Team, transfer.getD11Team());

        verify(this.transferRepository, times(1)).save(eq(transfer));
    }

    // updateTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferService::updateTransfer with invalid transfer id.
     */
    @Test
    void testUpdateTransferInvalidTransferId() {
        final BadRequestException e =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.updateTransfer(null, new TransferInput(5, 0, 0, 0)));

        assertEquals("transferId", e.getParameter());
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), e.getMessage());
    }

    /**
     * Tests TransferService::updateTransfer with invalid fee.
     */
    @Test
    void testUpdateTransferInvalidFee() {
        final String feeProperty = "fee";

        final BadRequestException negativeFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.updateTransfer(1L, new TransferInput(-5, 0, 0, 0)));

        assertEquals(feeProperty, negativeFeeException.getParameter());
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), negativeFeeException.getMessage());

        final BadRequestException invalidFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.updateTransfer(1L, new TransferInput(1, 0, 0, 0)));

        assertEquals(feeProperty, invalidFeeException.getParameter());
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), invalidFeeException.getMessage());
    }

    /**
     * Tests TransferService::updateTransfer with transfer not found.
     */
    @Test
    void testUpdateTransferTransferNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferRepository.findById(eq(1L))).thenReturn(Optional.empty());

        final NotFoundException e = assertThrows(NotFoundException.class,
                                                 () -> this.transferService.updateTransfer(1L, transferInput));

        assertEquals(Transfer.class.getSimpleName(), e.getResource());
        assertEquals(1L, e.getId());
    }

    /**
     * Tests TransferService::updateTransfer with transfer day not found.
     */
    @Test
    void testUpdateTransferTransferDayNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferRepository.findById(eq(1L))).thenReturn(Optional.of(generate(Transfer.class)));
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId()))).thenReturn(Optional.empty());

        final NotFoundException e = assertThrows(NotFoundException.class,
                                                 () -> this.transferService.updateTransfer(1L, transferInput));

        assertEquals(TransferDay.class.getSimpleName(), e.getResource());
        assertEquals(transferInput.transferDayId(), e.getId());
    }

    /**
     * Tests TransferService::updateTransfer with player not found.
     */
    @Test
    void testUpdateTransferPlayerNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferRepository.findById(eq(1L))).thenReturn(Optional.of(generate(Transfer.class)));
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId())))
                .thenReturn(Optional.of(generate(TransferDay.class)));
        when(this.playerRepository.findById(eq(transferInput.playerId()))).thenReturn(Optional.empty());

        final NotFoundException e = assertThrows(NotFoundException.class,
                                                 () -> this.transferService.updateTransfer(1L, transferInput));

        assertEquals(Player.class.getSimpleName(), e.getResource());
        assertEquals(transferInput.playerId(), e.getId());
    }

    /**
     * Tests TransferService::updateTransfer with D11 team not found.
     */
    @Test
    void testUpdateTransferD11TeamNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferRepository.findById(eq(1L))).thenReturn(Optional.of(generate(Transfer.class)));
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId())))
                .thenReturn(Optional.of(generate(TransferDay.class)));
        when(this.playerRepository.findById(eq(transferInput.playerId())))
                .thenReturn(Optional.of(generate(Player.class)));
        when(this.d11TeamRepository.findById(eq(transferInput.d11TeamId()))).thenReturn(Optional.empty());

        final NotFoundException e = assertThrows(NotFoundException.class,
                                                 () -> this.transferService.updateTransfer(1L, transferInput));

        assertEquals(D11Team.class.getSimpleName(), e.getResource());
        assertEquals(transferInput.d11TeamId(), e.getId());
    }

    /**
     * Tests TransferService::updateTransfer.
     */
    @Test
    void testUpdateTransfer() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        final Transfer transfer = generate(Transfer.class);
        final TransferDay transferDay = generate(TransferDay.class);
        final Player player = generate(Player.class);
        final D11Team d11Team = generate(D11Team.class);

        when(this.transferRepository.findById(eq(1L))).thenReturn(Optional.of(transfer));
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId())))
                .thenReturn(Optional.of(transferDay));
        when(this.playerRepository.findById(eq(transferInput.playerId()))).thenReturn(Optional.of(player));
        when(this.d11TeamRepository.findById(eq(transferInput.d11TeamId()))).thenReturn(Optional.of(d11Team));
        when(this.transferRepository.save(any(Transfer.class))).then(AdditionalAnswers.returnsFirstArg());

        final Transfer updatedTransfer = this.transferService.updateTransfer(1L, transferInput);

        assertNotNull(updatedTransfer);
        assertEquals(transferInput.fee(), updatedTransfer.getFee());
        assertEquals(transferDay, updatedTransfer.getTransferDay());
        assertEquals(player, updatedTransfer.getPlayer());
        assertEquals(d11Team, updatedTransfer.getD11Team());

        verify(this.transferRepository, times(1)).save(eq(transfer));
    }

    // deleteTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferService::deleteTransfer with not found.
     */
    @Test
    void testDeleteTransferNotFound() {
        final Transfer transfer = new Transfer();
        transfer.setId(1L);

        when(this.transferRepository.findById(eq(transfer.getId()))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                     () -> this.transferService.deleteTransfer(transfer.getId()));
    }

    /**
     * Tests TransferService::deleteTransfer.
     */
    @Test
    void testDeleteTransfer() {
        final Transfer transfer = new Transfer();
        transfer.setId(1L);

        when(this.transferRepository.findById(eq(transfer.getId()))).thenReturn(Optional.of(transfer));

        assertDoesNotThrow(() -> this.transferService.deleteTransfer(transfer.getId()));

        verify(this.transferRepository, times(1)).delete(eq(transfer));
    }

}
