package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferBidApi;
import org.d11.boot.api.v2.model.CreateTransferBidRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferBidDTO;
import org.d11.boot.api.v2.model.TransferBidInputDTO;
import org.d11.boot.api.v2.model.TransferBidsResponseBodyDTO;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.PlayerTransferContextRepository;
import org.d11.boot.spring.repository.TransferBidRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
     * Player transfer context service.
     */
    @Autowired
    private PlayerTransferContextRepository playerTransferContextRepository;

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

    // createTransferBid -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidControllerV2::createTransferBid for unauthorized.
     */
    @Test
    void testCreateTransferBidUnauthorized() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);
        final CreateTransferBidRequestBodyDTO requestBody = new CreateTransferBidRequestBodyDTO()
                .transferBid(new TransferBidInputDTO()
                                     .playerId(1L)
                                     .fee(10));

        assertThrows(FeignException.Unauthorized.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid unauthorized throws");
    }

    /**
     * Tests TransferBidControllerV2::createTransferBid for null player id.
     */
    @Test
    void testCreateTransferBidNullPlayerId() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);
        final CreateTransferBidRequestBodyDTO requestBody = new CreateTransferBidRequestBodyDTO()
                .transferBid(new TransferBidInputDTO()
                                     .fee(10));

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid null playerId throws");
    }

    /**
     * Tests TransferBidControllerV2::createTransferBid for null fee.
     */
    @Test
    void testCreateTransferBidNullFee() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);
        final CreateTransferBidRequestBodyDTO requestBody = new CreateTransferBidRequestBodyDTO()
                .transferBid(new TransferBidInputDTO()
                                     .playerId(1L));

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid null fee throws");
    }

    /**
     * Tests TransferBidControllerV2::createTransferBid for invalid fee.
     */
    @Test
    void testCreateTransferBidTransferBidInvalidFee() {
        TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(RuntimeException::new);
        transferDay.setStatus(Status.ACTIVE);
        transferDay = this.transferDayRepository.save(transferDay);

        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);
        final CreateTransferBidRequestBodyDTO requestBody = new CreateTransferBidRequestBodyDTO()
                .transferBid(new TransferBidInputDTO()
                                     .playerId(2L)
                                     .fee(0));

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid 0 fee throws");

        requestBody.getTransferBid().setFee(1000);

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid fee > maxBid throws");

        requestBody.getTransferBid().setFee(1);

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid fee not divisible by 5 throws");

        // @DirtiesContext won't work here for some reason. Maybe figure it out later
        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);
    }

    /**
     * Tests TransferBidControllerV2::createTransferBid for transfer bid not allowed.
     */
    @Test
    void testCreateTransferBidTransferBidNotAllowed() {
        final TransferBidApi transferBidApi = getAdministratorApi(TransferBidApi.class);
        final CreateTransferBidRequestBodyDTO requestBody = new CreateTransferBidRequestBodyDTO()
                .transferBid(new TransferBidInputDTO()
                                     .playerId(1L)
                                     .fee(10));

        assertThrows(FeignException.Conflict.class, () -> transferBidApi.createTransferBid(requestBody),
                     "TransferBidControllerV2::createTransferBid transfer bid not allowed throws");
    }

    // deleteTransferBid -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for unauthorized.
     */
    @Test
    void testDeleteTransferBidUnauthorized() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);

        assertThrows(FeignException.Unauthorized.class, () -> transferBidApi.deleteTransferBid(1L),
                     "TransferBidControllerV2::deleteTransferBid unauthorized throws");
    }

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for forbidden.
     */
    @Test
    void testDeleteTransferBidForbidden() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        assertThrows(FeignException.Forbidden.class, () -> transferBidApi.deleteTransferBid(1L),
                     "TransferBidControllerV2::deleteTransferBid forbidden throws");
    }

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for not found.
     */
    @Test
    void testDeleteTransferBidNotFound() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        assertThrows(FeignException.NotFound.class, () -> transferBidApi.deleteTransferBid(-1L),
                     "TransferBidControllerV2::deleteTransferBid not found throws");
    }

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for invalid transfer day status.
     */
    @Test
    void testDeleteTransferBidInvalidTransferDayStatus() {
        final TransferBidApi transferBidApi = getAdministratorApi(TransferBidApi.class);

        assertThrows(FeignException.Conflict.class, () -> transferBidApi.deleteTransferBid(1L),
                     "TransferBidControllerV2::deleteTransferBid invalid transferDay status throws");
    }

    // createTransferBid + deleteTransferBid ---------------------------------------------------------------------------

    /**
     * Tests TransferBidControllerV2::createTransferBid and TransferBidControllerV2::deleteTransferBid.
     */
    @Test
    void testCreateTransferBid() {
        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(RuntimeException::new);
        transferDay.setStatus(Status.ACTIVE);
        this.transferDayRepository.save(transferDay);

        final PlayerTransferContext playerTransferContext =
                this.playerTransferContextRepository.findByPlayerIdAndOwnerId(2L, 1L)
                        .orElse(new PlayerTransferContext());

        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);
        final CreateTransferBidRequestBodyDTO requestBody = new CreateTransferBidRequestBodyDTO()
                .transferBid(new TransferBidInputDTO()
                                     .playerId(2L)
                                     .fee(5));

        final TransferBidDTO result = transferBidApi.createTransferBid(requestBody).getTransferBid();

        assertNotNull(result, "TransferBidControllerV2::createTransferBid response not null");
        assertEquals(playerTransferContext.getTransferListing().getRanking(), result.getPlayerRanking(),
                     "TransferBidControllerV2::createTransferBid playerRanking equals");
        assertEquals(playerTransferContext.getRanking(), result.getD11TeamRanking(),
                     "TransferBidControllerV2::createTransferBid d11TeamRanking equals");
        assertEquals(requestBody.getTransferBid().getFee(), result.getFee(),
                     "TransferBidControllerV2::createTransferBid fee equals");
        assertEquals(requestBody.getTransferBid().getFee(), result.getActiveFee(),
                     "TransferBidControllerV2::createTransferBid activeFee equals");
        assertFalse(result.isSuccessful(), "TransferBidControllerV2::createTransferBid successful");
        assertEquals(requestBody.getTransferBid().getPlayerId(), result.getPlayer().getId(),
                     "TransferBidControllerV2::createTransferBid playerId equals");
        assertEquals(playerTransferContext.getD11Team().getId(), result.getD11Team().getId(),
                     "TransferBidControllerV2::createTransferBid d11TeamId equals");

        final Optional<TransferBid> created = this.transferBidRepository.findById(result.getId());
        assertTrue(created.isPresent(), "TransferBidControllerV2::createTransferBid transferBid present");

        assertDoesNotThrow(() -> transferBidApi.deleteTransferBid(result.getId()),
                           "TransferBidControllerV2::deleteTransferBid does not throw");

        final Optional<TransferBid> deleted = this.transferBidRepository.findById(result.getId());
        assertFalse(deleted.isPresent(), "TransferBidControllerV2::deleteTransferBid transferBid present");

        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);
    }

}
