package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferBidApi;
import org.d11.boot.api.v2.model.CreateTransferBidRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferBidDTO;
import org.d11.boot.api.v2.model.TransferBidFeeInputDTO;
import org.d11.boot.api.v2.model.TransferBidInputDTO;
import org.d11.boot.api.v2.model.TransferBidsResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdateTransferBidFeeRequestBodyDTO;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.PlayerTransferContextRepository;
import org.d11.boot.spring.repository.SeasonRepository;
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
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Tests TransferBidControllerV2::getTransferBidsByTransferDayId.
     */
    @Test
    @Transactional
    void testGetTransferBidsByTransferDayId() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.getTransferBidsByTransferDayId((Long) null));

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.getTransferBidsByTransferDayId(-1L));

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertTrue(transferDays.size() > 1);

        int finishedCount = 0;
        int notFinishedCount = 0;

        for (final TransferDay transferDay : transferDays) {
            final TransferBidsResponseBodyDTO transferBidsResponseBodyDTO =
                    transferBidApi.getTransferBidsByTransferDayId(transferDay.getId());
            assertNotNull(transferBidsResponseBodyDTO);

            final List<TransferBidDTO> result = transferBidsResponseBodyDTO.getTransferBids();

            assertNotNull(result);

            if (Status.FINISHED.equals(transferDay.getStatus())) {
                assertFalse(result.isEmpty());

                final List<TransferBid> transferBids = this.transferBidRepository
                        .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDay.getId());

                assertEquals(map(transferBids, TransferBidDTO.class), result);
                ++finishedCount;
            } else {
                assertTrue(result.isEmpty());
                if (!Status.PENDING.equals(transferDay.getStatus())) {
                    assertFalse(transferDay.getTransferBids().isEmpty());
                }
                ++notFinishedCount;
            }
        }

        // Make sure that we have both finished and not finished test transfer days
        assertTrue(finishedCount > 0 && notFinishedCount > 0);
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

        assertThrows(FeignException.Unauthorized.class, () -> transferBidApi.createTransferBid(requestBody));
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

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody));
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

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody));
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

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody));

        requestBody.getTransferBid().setFee(1000);

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody));

        requestBody.getTransferBid().setFee(1);

        assertThrows(FeignException.BadRequest.class, () -> transferBidApi.createTransferBid(requestBody));

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

        assertThrows(FeignException.Conflict.class, () -> transferBidApi.createTransferBid(requestBody));
    }

    // updateTransferBidFee --------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidControllerV2::updateTransferBidFee.
     */
    @Test
    void testUpdateTransferBid() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(RuntimeException::new);
        transferDay.setStatus(Status.ACTIVE);
        this.transferDayRepository.save(transferDay);

        final Long transferBidId = transferBidApi.createTransferBid(new CreateTransferBidRequestBodyDTO()
                                                                            .transferBid(new TransferBidInputDTO()
                                                                                                 .playerId(2L)
                                                                                                 .fee(5)))
                .getTransferBid().getId();

        final UpdateTransferBidFeeRequestBodyDTO requestBody = new UpdateTransferBidFeeRequestBodyDTO()
                .transferBid(new TransferBidFeeInputDTO().fee(10));

        final TransferBidDTO result = transferBidApi.updateTransferBidFee(transferBidId, requestBody).getTransferBid();

        assertNotNull(result);
        assertEquals(requestBody.getTransferBid().getFee(), result.getFee());
        assertEquals(requestBody.getTransferBid().getFee(), result.getActiveFee());

        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);
        this.transferBidRepository.deleteById(transferBidId);
    }

    /**
     * Tests TransferBidControllerV2::updateTransferBidFee for invalid fee.
     */
    @Test
    void testUpdateTransferBidFeeInvalidFee() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(RuntimeException::new);
        transferDay.setStatus(Status.ACTIVE);
        this.transferDayRepository.save(transferDay);

        final Long transferBidId = transferBidApi.createTransferBid(new CreateTransferBidRequestBodyDTO()
                                                                               .transferBid(new TransferBidInputDTO()
                                                                                                    .playerId(2L)
                                                                                                    .fee(5)))
                .getTransferBid().getId();

        final UpdateTransferBidFeeRequestBodyDTO requestBody = new UpdateTransferBidFeeRequestBodyDTO()
                .transferBid(new TransferBidFeeInputDTO());

        assertThrows(FeignException.BadRequest.class,
                     () -> transferBidApi.updateTransferBidFee(transferBidId, requestBody));

        requestBody.getTransferBid().setFee(-5);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferBidApi.updateTransferBidFee(transferBidId, requestBody));

        final PlayerTransferContext playerTransferContext =
                this.playerTransferContextRepository.findByPlayerIdAndOwnerId(2L, 1L)
                        .orElse(new PlayerTransferContext());

        requestBody.getTransferBid().setFee(playerTransferContext.getMaxBid() + 5);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferBidApi.updateTransferBidFee(transferBidId, requestBody));

        requestBody.getTransferBid().setFee(1);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferBidApi.updateTransferBidFee(transferBidId, requestBody));

        // @DirtiesContext won't work here either
        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);
        this.transferBidRepository.deleteById(transferBidId);
    }

    /**
     * Tests TransferBidControllerV2::updateTransferBidFee for unauthorized.
     */
    @Test
    void testUpdateTransferBidFeeUnauthorized() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);
        final UpdateTransferBidFeeRequestBodyDTO requestBody = new UpdateTransferBidFeeRequestBodyDTO()
                .transferBid(new TransferBidFeeInputDTO()
                                     .fee(10));

        assertThrows(FeignException.Unauthorized.class, () -> transferBidApi.updateTransferBidFee(1L, requestBody));
    }

    /**
     * Tests TransferBidControllerV2::updateTransferBidFee for forbidden.
     */
    @Test
    void testUpdateTransferBidFeeForbidden() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(RuntimeException::new);
        transferDay.setStatus(Status.ACTIVE);
        this.transferDayRepository.save(transferDay);

        final UpdateTransferBidFeeRequestBodyDTO requestBody = new UpdateTransferBidFeeRequestBodyDTO()
                .transferBid(new TransferBidFeeInputDTO()
                                     .fee(10));

        assertThrows(FeignException.Forbidden.class, () -> transferBidApi.updateTransferBidFee(16L, requestBody));

        // See above
        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);
    }

    /**
     * Tests TransferBidControllerV2::updateTransferBidFee for not found.
     */
    @Test
    void testUpdateTransferBidFeeNotFound() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);
        final UpdateTransferBidFeeRequestBodyDTO requestBody = new UpdateTransferBidFeeRequestBodyDTO()
                .transferBid(new TransferBidFeeInputDTO()
                                     .fee(10));

        assertThrows(FeignException.NotFound.class, () -> transferBidApi.updateTransferBidFee(-1L, requestBody));
    }

    /**
     * Tests TransferBidControllerV2::updateTransferBidFee for transfer bid not allowed.
     */
    @Test
    void testUpdateTransferBidFeeTransferBidNotAllowed() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(RuntimeException::new);
        transferDay.setStatus(Status.ACTIVE);
        this.transferDayRepository.save(transferDay);

        final Long transferBidId = transferBidApi.createTransferBid(new CreateTransferBidRequestBodyDTO()
                                                                            .transferBid(new TransferBidInputDTO()
                                                                                                 .playerId(2L)
                                                                                                 .fee(5)))
                .getTransferBid().getId();

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(RuntimeException::new);
        final int d11TeamBudget = season.getD11TeamBudget();
        season.setD11TeamBudget(1);
        this.seasonRepository.save(season);

        final UpdateTransferBidFeeRequestBodyDTO requestBody = new UpdateTransferBidFeeRequestBodyDTO()
                .transferBid(new TransferBidFeeInputDTO()
                                     .fee(10));

        assertThrows(FeignException.Conflict.class,
                     () -> transferBidApi.updateTransferBidFee(transferBidId, requestBody));

        // Clean up, @DirtiesContext won't work here either
        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);

        season.setD11TeamBudget(d11TeamBudget);
        this.seasonRepository.save(season);

        this.transferBidRepository.deleteById(transferBidId);
    }

    // deleteTransferBid -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for unauthorized.
     */
    @Test
    void testDeleteTransferBidUnauthorized() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);

        assertThrows(FeignException.Unauthorized.class, () -> transferBidApi.deleteTransferBid(1L));
    }

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for forbidden.
     */
    @Test
    void testDeleteTransferBidForbidden() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        assertThrows(FeignException.Forbidden.class, () -> transferBidApi.deleteTransferBid(1L));
    }

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for not found.
     */
    @Test
    void testDeleteTransferBidNotFound() {
        final TransferBidApi transferBidApi = getUserApi(TransferBidApi.class);

        assertThrows(FeignException.NotFound.class, () -> transferBidApi.deleteTransferBid(-1L));
    }

    /**
     * Tests TransferBidControllerV2::deleteTransferBid for invalid transfer day status.
     */
    @Test
    void testDeleteTransferBidInvalidTransferDayStatus() {
        final TransferBidApi transferBidApi = getAdministratorApi(TransferBidApi.class);

        assertThrows(FeignException.Conflict.class, () -> transferBidApi.deleteTransferBid(1L));
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

        assertNotNull(result);
        assertEquals(playerTransferContext.getTransferListing().getRanking(), result.getPlayerRanking());
        assertEquals(playerTransferContext.getRanking(), result.getD11TeamRanking());
        assertEquals(requestBody.getTransferBid().getFee(), result.getFee());
        assertEquals(requestBody.getTransferBid().getFee(), result.getActiveFee());
        assertFalse(result.isSuccessful());
        assertEquals(requestBody.getTransferBid().getPlayerId(), result.getPlayer().getId());
        assertEquals(playerTransferContext.getD11Team().getId(), result.getD11Team().getId());

        final Optional<TransferBid> created = this.transferBidRepository.findById(result.getId());
        assertTrue(created.isPresent());

        assertDoesNotThrow(() -> transferBidApi.deleteTransferBid(result.getId()));

        final Optional<TransferBid> deleted = this.transferBidRepository.findById(result.getId());
        assertFalse(deleted.isPresent());

        transferDay.setStatus(Status.PENDING);
        this.transferDayRepository.save(transferDay);
    }

}
