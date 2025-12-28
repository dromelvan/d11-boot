package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.TransferListingApi;
import org.d11.boot.api.v2.model.CreateTransferListingRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferListingDTO;
import org.d11.boot.api.v2.model.TransferListingResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferListingsResponseBodyDTO;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferListingRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
     * Transfer listable player id.
     */
    private static final Long PLAYER_ID = 7L;

    /**
     * Transfer listing repository.
     */
    @Autowired
    private TransferListingRepository transferListingRepository;

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Player season stat repository.
     */
    @Autowired
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    // createTransferListing -------------------------------------------------------------------------------------------

    /**
     * Tests TransferListingControllerV2::createTransferListing for null playerId.
     */
    @Test
    void testCreateTransferListingPlayerIdNull() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferListingApi.createTransferListing(new CreateTransferListingRequestBodyDTO()));
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for unauthorized.
     */
    @Test
    void testCreateTransferListingUnauthorized() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(1L)));
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for forbidden.
     */
    @Test
    void testCreateTransferListingForbidden() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertThrows(FeignException.Forbidden.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(3L)));
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for playerSeasonStat not found.
     */
    @Test
    void testCreateTransferListingPlayerSeasonStatNotFound() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(1_000L)));

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_NO_PLAYER_SEASON_STAT.getMessage()));
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for D11 team max transfers.
     */
    @Test
    void testCreateTransferListingD11TeamMaxTransfers() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NullPointerException::new);
        final int d11TeamMaxTransfers = season.getD11TeamMaxTransfers();
        season.setD11TeamMaxTransfers(0);
        season = this.seasonRepository.save(season);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(PLAYER_ID)));

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_NO_REMAINING_D11_TEAM_TRANSFERS.getMessage()));

        season.setD11TeamMaxTransfers(d11TeamMaxTransfers);
        this.seasonRepository.save(season);
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for transfer day invalid status.
     */
    @Test
    void testCreateTransferListingTransferDayStatusInvalid() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(NullPointerException::new);
        final Status status = transferDay.getStatus();
        transferDay.setStatus(Status.FINISHED);
        transferDay = this.transferDayRepository.save(transferDay);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(PLAYER_ID)));

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS.getMessage()));

        transferDay.setStatus(status);
        this.transferDayRepository.save(transferDay);
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for player already transfer listed.
     */
    @Test
    void testCreateTransferListingPlayerAlreadyTransferListed() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(1L)));

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_NON_UNIQUE_TRANSFER_LISTING.getMessage()));
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for user.
     */
    @Test
    void testCreateTransferListingUser() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        final TransferListingResponseBodyDTO responseBody = transferListingApi
                .createTransferListing(new CreateTransferListingRequestBodyDTO().playerId(PLAYER_ID));

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NullPointerException::new);
        final PlayerSeasonStat playerSeasonStat = this.playerSeasonStatRepository
                .findByPlayerIdAndSeasonId(PLAYER_ID, season.getId()).orElseThrow(NullPointerException::new);

        assertResultEquals(playerSeasonStat, responseBody.getTransferListing());

        this.transferListingRepository.deleteById(responseBody.getTransferListing().getId());
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for administrator.
     */
    @Test
    void testCreateTransferListingAdministrator() {
        final TransferListingApi transferListingApi = getAdministratorApi(TransferListingApi.class);

        final TransferListingResponseBodyDTO responseBody = transferListingApi
                .createTransferListing(new CreateTransferListingRequestBodyDTO().playerId(PLAYER_ID));

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NullPointerException::new);
        final PlayerSeasonStat playerSeasonStat = this.playerSeasonStatRepository
                .findByPlayerIdAndSeasonId(PLAYER_ID, season.getId()).orElseThrow(NullPointerException::new);

        assertResultEquals(playerSeasonStat, responseBody.getTransferListing());

        this.transferListingRepository.deleteById(responseBody.getTransferListing().getId());
    }

    // deleteTransferListing -------------------------------------------------------------------------------------------

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for unauthorized.
     */
    @Test
    void testDeleteTransferListingUnauthorized() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);

        assertThrows(FeignException.Unauthorized.class, () -> transferListingApi.deleteTransferListing(1L));
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for forbidden.
     */
    @Test
    void testDeleteTransferListingForbidden() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertThrows(FeignException.Forbidden.class, () -> transferListingApi.deleteTransferListing(12L));
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for transferListing not found.
     */
    @Test
    void testDeleteTransferListingTransferListingNotFound() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertThrows(FeignException.NotFound.class, () -> transferListingApi.deleteTransferListing(1_000L));
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for transfer day invalid status as user.
     */
    @Test
    void testDeleteTransferListingTransferDayInvalidStatusUser() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                                                       () -> transferListingApi.deleteTransferListing(13L));

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS.getMessage()));
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for transfer day invalid status as admin.
     */
    @Test
    void testDeleteTransferListingTransferDayInvalidStatusAdmin() {
        final TransferListingApi transferListingApi = getAdministratorApi(TransferListingApi.class);

        assertDoesNotThrow(() -> transferListingApi.deleteTransferListing(13L));

        assertFalse(this.transferListingRepository.findById(13L).isPresent());
    }

    /**
     * Tests TransferListingService::deleteTransferListing as user.
     */
    @Test
    void testDeleteTransferListingUser() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertDoesNotThrow(() -> transferListingApi.deleteTransferListing(14L));

        assertFalse(this.transferListingRepository.findById(14L).isPresent());
    }

    /**
     * Tests TransferListingService::deleteTransferListing as admin.
     */
    @Test
    void testDeleteTransferListingAdmin() {
        final TransferListingApi transferListingApi = getAdministratorApi(TransferListingApi.class);

        assertDoesNotThrow(() -> transferListingApi.deleteTransferListing(15L));

        assertFalse(this.transferListingRepository.findById(15L).isPresent());
    }

    // getTransferListingsByTransferDayId ------------------------------------------------------------------------------

    /**
     * Tests TransferListingControllerV2::getTransferListingsByTransferDayId.
     */
    @Test
    @Transactional
    void testGetTransferListingsByTransferDayId() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferListingApi.getTransferListingsByTransferDayId(null, 1));

        assertThrows(FeignException.BadRequest.class,
                     () -> transferListingApi.getTransferListingsByTransferDayId(1L, null));

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertTrue(transferDays.size() > 1);

        int pendingCount = 0;
        int notPendingCount = 0;

        for (final TransferDay transferDay : transferDays) {
            final TransferListingsResponseBodyDTO transferListingsResponseBodyDTO =
                    transferListingApi.getTransferListingsByTransferDayId(transferDay.getId(), 0);
            assertNotNull(transferListingsResponseBodyDTO);

            final List<TransferListingDTO> result = transferListingsResponseBodyDTO.getTransferListings();

            assertNotNull(result);

            if (Status.PENDING.equals(transferDay.getStatus())) {
                assertTrue(result.isEmpty());
                assertFalse(transferDay.getTransferListings().isEmpty());
                ++pendingCount;
            } else {
                assertFalse(result.isEmpty());
                assertEquals(map(transferDay.getTransferListings(), TransferListingDTO.class), result);
                ++notPendingCount;
            }
        }

        // Make sure that we have both pending and not pending test transfer days
        assertTrue(pendingCount > 0 && notPendingCount > 0);
    }

    /**
     * Checks a player season stat and a transfer listing DTO for equality.
     *
     * @param playerSeasonStat The player season stat.
     * @param result The result.
     */
    private void assertResultEquals(final PlayerSeasonStat playerSeasonStat, final TransferListingDTO result) {
        assertEquals(playerSeasonStat.getPlayer().getId(), result.getPlayer().getId());
        assertEquals(playerSeasonStat.getTeam().getId(), result.getTeam().getId());
        assertEquals(playerSeasonStat.getD11Team().getId(), result.getD11Team().getId());
        assertEquals(playerSeasonStat.getPosition().getId(), result.getPosition().getId());
        assertEquals(playerSeasonStat.getFormPoints(), result.getFormPoints());
        assertEquals(playerSeasonStat.getFormMatchPoints(), result.getFormMatchPoints());
        assertEquals(playerSeasonStat.getRanking(), result.getRanking());
        assertEquals(playerSeasonStat.getPointsPerAppearance(), result.getPointsPerAppearance());
        assertEquals(playerSeasonStat.getCleanSheets(), result.getCleanSheets());
        assertEquals(playerSeasonStat.getYellowCards(), result.getYellowCards());
        assertEquals(playerSeasonStat.getRedCards(), result.getRedCards());
        assertEquals(playerSeasonStat.getSubstitutionsOn(), result.getSubstitutionsOn());
        assertEquals(playerSeasonStat.getSubstitutionsOff(), result.getSubstitutionsOff());
        assertEquals(playerSeasonStat.getManOfTheMatch(), result.getManOfTheMatch());
        assertEquals(playerSeasonStat.getSharedManOfTheMatch(), result.getSharedManOfTheMatch());
        assertEquals(playerSeasonStat.getGamesStarted(), result.getGamesStarted());
        assertEquals(playerSeasonStat.getGamesSubstitute(), result.getGamesSubstitute());
        assertEquals(playerSeasonStat.getGamesDidNotParticipate(), result.getGamesDidNotParticipate());
        assertEquals(playerSeasonStat.getMinutesPlayed(), result.getMinutesPlayed());
        assertEquals(playerSeasonStat.getGoals(), result.getGoals());
        assertEquals(playerSeasonStat.getGoalAssists(), result.getGoalAssists());
        assertEquals(playerSeasonStat.getOwnGoals(), result.getOwnGoals());
        assertEquals(playerSeasonStat.getGoalsConceded(), result.getGoalsConceded());
        assertEquals(playerSeasonStat.getRating(), result.getRating());
        assertEquals(playerSeasonStat.getPoints(), result.getPoints());
    }

}
