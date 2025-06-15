package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.TransferApi;
import org.d11.boot.api.v2.model.CreateTransferRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferDTO;
import org.d11.boot.api.v2.model.TransferInputDTO;
import org.d11.boot.api.v2.model.TransferResponseBodyDTO;
import org.d11.boot.api.v2.model.TransfersResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdateTransferRequestBodyDTO;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.util.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer controller tests.
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class TransferControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Fee property name.
     */
    private static final String FEE = "fee";

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;

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
    void testGetTransfersByTransferDayId() {
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

    /**
     * Tests TransferControllerV2::getTransfersByPlayerId.
     */
    @Test
    @Transactional
    void testGetTransfersByPlayerId() {
        final TransferApi transferApi = getApi(TransferApi.class);

        assertThrows(FeignException.BadRequest.class,
                () -> transferApi.getTransfersByPlayerId((Long) null),
                "TransferControllerV2::getTransfersByPlayerId playerId null throws");

        assertThrows(FeignException.BadRequest.class,
                () -> transferApi.getTransfersByPlayerId(-1L),
                "TransferControllerV2::getTransfersByPlayerId playerId negative throws");

        final List<Player> players = this.playerRepository.findAll().stream()
                .filter(player ->
                        !this.transferRepository.findByPlayerIdOrderByTransferDayDatetimeDesc(player.getId()).isEmpty())
                .toList();

        assertFalse(players.isEmpty(), "TransferControllerV2::getTransfersByPlayerId players size > 0");

        for (final Player player : players) {
            final TransfersResponseBodyDTO transfersByPlayerId = transferApi.getTransfersByPlayerId(player.getId());
            assertNotNull(transfersByPlayerId, "TransferControllerV2::getTransfersByPlayerId response not null");

            final List<TransferDTO> result = transfersByPlayerId.getTransfers();

            assertNotNull(result, "TransferControllerV2::getTransfersByPlayerId not null ");

            final List<Transfer> transfers =
                    this.transferRepository.findByPlayerIdOrderByTransferDayDatetimeDesc(player.getId());

            assertEquals(map(transfers, TransferDTO.class), result,
                    "TransferControllerV2::getTransfersByPlayerId equals");
        }
    }

    // createTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferService::createTransfer.
     */
    @Test
    void testCreateTransfer() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        final TransferResponseBodyDTO response = transferApi.createTransfer(requestBodyDTO);

        assertNotNull(response, "TransferControllerV2::createTransfer response not null");
        assertNotNull(response.getTransfer(), "TransferControllerV2::createTransfer transfer not null");

        assertEquals(requestBodyDTO.getTransfer().getFee(), response.getTransfer().getFee(),
                     "TransferControllerV2::createTransfer fee equals");
        assertEquals(requestBodyDTO.getTransfer().getTransferDayId(), response.getTransfer().getTransferDay().getId(),
                     "TransferControllerV2::createTransfer transferDay.id equals");
        assertEquals(requestBodyDTO.getTransfer().getPlayerId(), response.getTransfer().getPlayer().getId(),
                     "TransferControllerV2::createTransfer player.id equals");
        assertEquals(requestBodyDTO.getTransfer().getD11TeamId(), response.getTransfer().getD11Team().getId(),
                     "TransferControllerV2::createTransfer d11Team.id equals");
    }

    /**
     * Tests TransferService::createTransfer with invalid fee.
     */
    @Test
    void testCreateTransferInvalidFee() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(-5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        final FeignException.BadRequest negativeFeeException =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferApi.createTransfer(requestBodyDTO),
                             "TransferControllerV2::createTransfer fee negative throws");

        assertTrue(negativeFeeException.getMessage().contains(FEE),
                   "TransferControllerV2::updateTransfer fee negative property equals");
        assertTrue(negativeFeeException.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()),
                   "TransferControllerV2::updateTransfer fee negative message contains");

        requestBodyDTO.getTransfer().setFee(1);

        final FeignException.BadRequest invalidFeeException =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferApi.createTransfer(requestBodyDTO),
                             "TransferControllerV2::createTransfer fee invalid throws");

        assertTrue(invalidFeeException.getMessage().contains(FEE),
                   "TransferControllerV2::updateTransfer fee invalid property equals");
        assertTrue(invalidFeeException.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()),
                   "TransferControllerV2::updateTransfer fee invalid message contains");
    }

    /**
     * Tests TransferService::createTransfer for unauthorized.
     */
    @Test
    void testCreateTransferUnauthorized() {
        final TransferApi transferApi = getApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferApi.createTransfer(requestBodyDTO),
                     "TransferControllerV2::createTransfer unauthorized throws");
    }

    /**
     * Tests TransferService::createTransfer for forbidden.
     */
    @Test
    void testCreateTransferForbidden() {
        final TransferApi transferApi = getUserApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        assertThrows(FeignException.Forbidden.class,
                     () -> transferApi.createTransfer(requestBodyDTO),
                     "TransferControllerV2::createTransfer forbidden throws");
    }

    /**
     * Tests TransferService::createTransfer with transfer day not found.
     */
    @Test
    void testCreateTransferTransferDayNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(-1L).playerId(1L).d11TeamId(1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.createTransfer(requestBodyDTO),
                             "TransferControllerV2::createTransfer transferDay not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::createTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"TransferDay\""),
                   "TransferControllerV2::createTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":" + requestBodyDTO.getTransfer().getTransferDayId()),
                   "TransferControllerV2::createTransfer message contains transferDay id");
    }

    /**
     * Tests TransferService::createTransfer with player not found.
     */
    @Test
    void testCreateTransferPlayerNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(-1L).d11TeamId(1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.createTransfer(requestBodyDTO),
                             "TransferControllerV2::createTransfer player not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::createTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"Player\""),
                   "TransferControllerV2::createTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":" + requestBodyDTO.getTransfer().getPlayerId()),
                   "TransferControllerV2::createTransfer message contains player id");
    }

    /**
     * Tests TransferService::createTransfer with D11 team not found.
     */
    @Test
    void testCreateTransferD11TeamNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final CreateTransferRequestBodyDTO requestBodyDTO = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(-1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.createTransfer(requestBodyDTO),
                             "TransferControllerV2::createTransfer D11 team not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::createTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"D11Team\""),
                   "TransferControllerV2::createTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":" + requestBodyDTO.getTransfer().getD11TeamId()),
                   "TransferControllerV2::createTransfer message contains D11 team id");
    }

    // updateTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferService::updateTransfer.
     */
    @Test
    void testUpdateTransfer() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        final TransferResponseBodyDTO response = transferApi.updateTransfer(1L, requestBodyDTO);

        assertNotNull(response, "TransferControllerV2::updateTransfer response not null");
        assertNotNull(response.getTransfer(), "TransferControllerV2::updateTransfer transfer not null");

        assertEquals(requestBodyDTO.getTransfer().getFee(), response.getTransfer().getFee(),
                     "TransferControllerV2::updateTransfer fee equals");
        assertEquals(requestBodyDTO.getTransfer().getTransferDayId(), response.getTransfer().getTransferDay().getId(),
                     "TransferControllerV2::updateTransfer transferDay.id equals");
        assertEquals(requestBodyDTO.getTransfer().getPlayerId(), response.getTransfer().getPlayer().getId(),
                     "TransferControllerV2::updateTransfer player.id equals");
        assertEquals(requestBodyDTO.getTransfer().getD11TeamId(), response.getTransfer().getD11Team().getId(),
                     "TransferControllerV2::updateTransfer d11Team.id equals");
    }

    /**
     * Tests TransferService::updateTransfer with invalid fee.
     */
    @Test
    void testUpdateTransferInvalidFee() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(-5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        final FeignException.BadRequest negativeFeeException =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferApi.updateTransfer(1L, requestBodyDTO),
                             "TransferControllerV2::createTransfer fee negative throws");

        assertTrue(negativeFeeException.getMessage().contains(FEE),
                   "TransferControllerV2::updateTransfer fee negative property equals");
        assertTrue(negativeFeeException.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()),
                   "TransferControllerV2::updateTransfer fee negative message contains");

        requestBodyDTO.getTransfer().setFee(1);

        final FeignException.BadRequest invalidFeeException =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferApi.updateTransfer(1L, requestBodyDTO),
                             "TransferControllerV2::updateTransfer fee invalid throws");

        assertTrue(invalidFeeException.getMessage().contains(FEE),
                   "TransferControllerV2::updateTransfer fee invalid property equals");
        assertTrue(invalidFeeException.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()),
                   "TransferControllerV2::updateTransfer fee invalid message contains");
    }

    /**
     * Tests TransferService::updateTransfer for unauthorized.
     */
    @Test
    void testUpdateTransferUnauthorized() {
        final TransferApi transferApi = getApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferApi.updateTransfer(1L, requestBodyDTO),
                     "TransferControllerV2::updateTransfer unauthorized throws");
    }

    /**
     * Tests TransferService::updateTransfer for forbidden.
     */
    @Test
    void testUpdateTransferForbidden() {
        final TransferApi transferApi = getUserApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        assertThrows(FeignException.Forbidden.class,
                     () -> transferApi.updateTransfer(1L, requestBodyDTO),
                     "TransferControllerV2::updateTransfer forbidden throws");
    }

    /**
     * Tests TransferService::updateTransfer with transfer not found.
     */
    @Test
    void testUpdateTransferTransferNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.updateTransfer(-1L, requestBodyDTO),
                             "TransferControllerV2::updateTransfer transfer not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::updateTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"Transfer\""),
                   "TransferControllerV2::updateTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":-1"),
                   "TransferControllerV2::updateTransfer message contains transfer id");
    }

    /**
     * Tests TransferService::updateTransfer with transfer day not found.
     */
    @Test
    void testUpdateTransferTransferDayNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(-1L).playerId(1L).d11TeamId(1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.updateTransfer(1L, requestBodyDTO),
                             "TransferControllerV2::updateTransfer transferDay not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::updateTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"TransferDay\""),
                   "TransferControllerV2::updateTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":" + requestBodyDTO.getTransfer().getTransferDayId()),
                   "TransferControllerV2::updateTransfer message contains transferDay id");
    }

    /**
     * Tests TransferService::updateTransfer with player not found.
     */
    @Test
    void testUpdateTransferPlayerNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(-1L).d11TeamId(1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.updateTransfer(1L, requestBodyDTO),
                             "TransferControllerV2::updateTransfer player not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::updateTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"Player\""),
                   "TransferControllerV2::updateTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":" + requestBodyDTO.getTransfer().getPlayerId()),
                   "TransferControllerV2::updateTransfer message contains player id");
    }

    /**
     * Tests TransferService::updateTransfer with D11 team not found.
     */
    @Test
    void testUpdateTransferD11TeamNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final UpdateTransferRequestBodyDTO requestBodyDTO = new UpdateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(-1L));

        final FeignException.NotFound e =
                assertThrows(FeignException.NotFound.class,
                             () -> transferApi.updateTransfer(1L, requestBodyDTO),
                             "TransferControllerV2::updateTransfer D11 team not found throws");

        assertTrue(e.getMessage().contains("\"error\":\"Not Found\""),
                   "TransferControllerV2::updateTransfer message contains error");
        assertTrue(e.getMessage().contains("\"resource\":\"D11Team\""),
                   "TransferControllerV2::updateTransfer message contains resource");
        assertTrue(e.getMessage().contains("\"id\":" + requestBodyDTO.getTransfer().getD11TeamId()),
                   "TransferControllerV2::updateTransfer message contains D11 team id");
    }

    // deleteTransfer --------------------------------------------------------------------------------------------------

    /**
     * Tests TransferController::deleteTransfer for unauthorized.
     */
    @Test
    void testDeleteTransferUnauthorized() {
        assertThrows(FeignException.Unauthorized.class,
                     () -> getApi(TransferApi.class).deleteTransfer(1L),
                     "TransferController::deleteTransfer unauthorized throws");
    }

    /**
     * Tests TransferController::deleteTransfer for forbidden.
     */
    @Test
    void testDeleteTransferForbidden() {
        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(TransferApi.class).deleteTransfer(1L),
                     "TransferController::deleteTransfer user throws");
    }

    /**
     * Tests TransferController::deleteTransfer for not found.
     */
    @Test
    void testDeleteTransferNotFound() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        assertThrows(FeignException.NotFound.class,
                     () -> transferApi.deleteTransfer(0L),
                     "TransferController::deleteTransfer not found throws");
    }

    /**
     * Tests TransferController::deleteTransfer.
     */
    @Test
    void testDeleteTransfer() {
        final TransferApi transferApi = getAdministratorApi(TransferApi.class);

        final CreateTransferRequestBodyDTO createRequest = new CreateTransferRequestBodyDTO()
                .transfer(new TransferInputDTO().fee(5).transferDayId(1L).playerId(1L).d11TeamId(1L));

        final TransferDTO transferDTO = transferApi.createTransfer(createRequest).getTransfer();

        assertDoesNotThrow(() -> transferApi.deleteTransfer(transferDTO.getId()),
                           "TransferController::deleteTransfer does not throw");

        final Optional<Transfer> optional = this.transferRepository.findById(transferDTO.getId());

        assertFalse(optional.isPresent(),
                    "TransferController::deleteTransfer transfer deleted present");
    }

}
