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

    // createTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferService::createTransfer with invalid fee.
     */
    @Test
    void testCreateTransferInvalidFee() {
        final String feeProperty = "fee";

        final BadRequestException negativeFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.createTransfer(new TransferInput(-5, 0, 0, 0)),
                             "TransferService::createTransfer negative fee throws");

        assertEquals(feeProperty, negativeFeeException.getParameter(),
                     "TransferService::createTransfer negative fee parameter equals");
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), negativeFeeException.getMessage(),
                     "TransferService::createTransfer negative fee message equals");

        final BadRequestException invalidFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.createTransfer(new TransferInput(1, 0, 0, 0)),
                             "TransferService::createTransfer invalid fee throws");

        assertEquals(feeProperty, invalidFeeException.getParameter(),
                     "TransferService::createTransfer invalid fee parameter equals");
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), invalidFeeException.getMessage(),
                     "TransferService::createTransfer invalid fee message equals");
    }

    /**
     * Tests TransferService::createTransfer with transfer day not found.
     */
    @Test
    void testCreateTransferTransferDayNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferDayRepository.findById(eq(transferInput.transferDayId()))).thenReturn(Optional.empty());

        final NotFoundException transferDayException =
                assertThrows(NotFoundException.class, () -> this.transferService.createTransfer(transferInput),
                             "TransferService::createTransfer transfer day not found throws");

        assertEquals(TransferDay.class.getSimpleName(), transferDayException.getResource(),
                     "TransferService::createTransfer transfer day not found resource equals");
        assertEquals(transferInput.transferDayId(), transferDayException.getId(),
                     "TransferService::createTransfer transfer day not found id equals");
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
                assertThrows(NotFoundException.class, () -> this.transferService.createTransfer(transferInput),
                             "TransferService::createTransfer player not found throws");

        assertEquals(Player.class.getSimpleName(), transferDayException.getResource(),
                     "TransferService::createTransfer player not found resource equals");
        assertEquals(transferInput.playerId(), transferDayException.getId(),
                     "TransferService::createTransfer player not found id equals");
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
                assertThrows(NotFoundException.class, () -> this.transferService.createTransfer(transferInput),
                             "TransferService::createTransfer D11 team not found throws");

        assertEquals(D11Team.class.getSimpleName(), transferDayException.getResource(),
                     "TransferService::createTransfer D11 team not found resource equals");
        assertEquals(transferInput.d11TeamId(), transferDayException.getId(),
                     "TransferService::createTransfer D11 team not found id equals");
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

        assertNotNull(transfer, "TransferService::createTransfer not null");
        assertEquals(transferInput.fee(), transfer.getFee(), "TransferService::createTransfer fee equals");
        assertEquals(transferDay, transfer.getTransferDay(), "TransferService::createTransfer transferDay equals");
        assertEquals(player, transfer.getPlayer(), "TransferService::createTransfer player equals");
        assertEquals(d11Team, transfer.getD11Team(), "TransferService::createTransfer d11Team equals");

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
                             () -> this.transferService.updateTransfer(null, new TransferInput(5, 0, 0, 0)),
                             "TransferService::updateTransfer invalid transfer id throws");

        assertEquals("transferId", e.getParameter(),
                     "TransferService::updateTransfer invalid transfer id parameter equals");
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), e.getMessage(),
                     "TransferService::updateTransfer invalid transfer id message equals");
    }

    /**
     * Tests TransferService::updateTransfer with invalid fee.
     */
    @Test
    void testUpdateTransferInvalidFee() {
        final String feeProperty = "fee";

        final BadRequestException negativeFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.updateTransfer(1L, new TransferInput(-5, 0, 0, 0)),
                             "TransferService::updateTransfer negative fee throws");

        assertEquals(feeProperty, negativeFeeException.getParameter(),
                     "TransferService::updateTransfer negative fee parameter equals");
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), negativeFeeException.getMessage(),
                     "TransferService::updateTransfer negative fee message equals");

        final BadRequestException invalidFeeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferService.updateTransfer(1L, new TransferInput(1, 0, 0, 0)),
                             "TransferService::updateTransfer invalid fee throws");

        assertEquals(feeProperty, invalidFeeException.getParameter(),
                     "TransferService::updateTransfer invalid fee parameter equals");
        assertEquals(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage(), invalidFeeException.getMessage(),
                     "TransferService::updateTransfer invalid fee message equals");
    }

    /**
     * Tests TransferService::updateTransfer with transfer not found.
     */
    @Test
    void testUpdateTransferTransferNotFound() {
        final TransferInput transferInput = new TransferInput(5, 1, 2, 3);
        when(this.transferRepository.findById(eq(1L))).thenReturn(Optional.empty());

        final NotFoundException e = assertThrows(NotFoundException.class,
                                                 () -> this.transferService.updateTransfer(1L, transferInput),
                                                 "TransferService::updateTransfer transfer not found throws");

        assertEquals(Transfer.class.getSimpleName(), e.getResource(),
                     "TransferService::updateTransfer transfer not found resource equals");
        assertEquals(1L, e.getId(), "TransferService::updateTransfer transfer not found id equals");
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
                                                 () -> this.transferService.updateTransfer(1L, transferInput),
                                                 "TransferService::updateTransfer transfer day not found throws");

        assertEquals(TransferDay.class.getSimpleName(), e.getResource(),
                     "TransferService::updateTransfer transfer day not found resource equals");
        assertEquals(transferInput.transferDayId(), e.getId(),
                     "TransferService::updateTransfer transfer day not found id equals");
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
                                                 () -> this.transferService.updateTransfer(1L, transferInput),
                                                 "TransferService::updateTransfer player not found throws");

        assertEquals(Player.class.getSimpleName(), e.getResource(),
                     "TransferService::updateTransfer player not found resource equals");
        assertEquals(transferInput.playerId(), e.getId(),
                     "TransferService::updateTransfer player not found id equals");
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
                                                 () -> this.transferService.updateTransfer(1L, transferInput),
                                                 "TransferService::updateTransfer D11 team not found throws");

        assertEquals(D11Team.class.getSimpleName(), e.getResource(),
                     "TransferService::updateTransfer D11 team not found resource equals");
        assertEquals(transferInput.d11TeamId(), e.getId(),
                     "TransferService::updateTransfer D11 team not found id equals");
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

        assertNotNull(updatedTransfer, "TransferService::updateTransfer not null");
        assertEquals(transferInput.fee(), updatedTransfer.getFee(), "TransferService::updateTransfer fee equals");
        assertEquals(transferDay, updatedTransfer.getTransferDay(),
                     "TransferService::updateTransfer transferDay equals");
        assertEquals(player, updatedTransfer.getPlayer(), "TransferService::updateTransfer player equals");
        assertEquals(d11Team, updatedTransfer.getD11Team(), "TransferService::updateTransfer d11Team equals");

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
                     () -> this.transferService.deleteTransfer(transfer.getId()),
                     "TransferService::deleteTransfer not found throws");
    }

    /**
     * Tests TransferService::deleteTransfer.
     */
    @Test
    void testDeleteTransfer() {
        final Transfer transfer = new Transfer();
        transfer.setId(1L);

        when(this.transferRepository.findById(eq(transfer.getId()))).thenReturn(Optional.of(transfer));

        assertDoesNotThrow(() -> this.transferService.deleteTransfer(transfer.getId()),
                           "TransferService::deleteTransfer does not throw");

        verify(this.transferRepository, times(1)).delete(eq(transfer));
    }

}
