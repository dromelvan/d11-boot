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
                     () -> transferListingApi.createTransferListing(new CreateTransferListingRequestBodyDTO()),
                     "TransferListingControllerV2::createTransferListing playerId null throws");
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for unauthorized.
     */
    @Test
    void testCreateTransferListingUnauthorized() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(1L)),
                     "TransferListingControllerV2::createTransferListing unauthorized throws");
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for forbidden.
     */
    @Test
    void testCreateTransferListingForbidden() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertThrows(FeignException.Forbidden.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(3L)),
                     "TransferListingControllerV2::createTransferListing forbidden throws");
    }

    /**
     * Tests TransferListingControllerV2::createTransferListing for playerSeasonStat not found.
     */
    @Test
    void testCreateTransferListingPlayerSeasonStatNotFound() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                     () -> transferListingApi.createTransferListing(
                             new CreateTransferListingRequestBodyDTO().playerId(1_000L)),
                     "TransferListingControllerV2::createTransferListing playerSeasonStat not found throws");

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_NO_PLAYER_SEASON_STAT.getMessage()),
                   "TransferListingControllerV2::createTransferListing playerSeasonStat not found message contains");
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
                             new CreateTransferListingRequestBodyDTO().playerId(PLAYER_ID)),
                     "TransferListingControllerV2::createTransferListing D11 team max transfers throws");

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_NO_REMAINING_D11_TEAM_TRANSFERS.getMessage()),
                   "TransferListingControllerV2::createTransferListing D11 team max transfers message contains");

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
                             new CreateTransferListingRequestBodyDTO().playerId(PLAYER_ID)),
                     "TransferListingControllerV2::createTransferListing transfer day invalid status throws");

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS.getMessage()),
                   "TransferListingControllerV2::createTransferListing transfer day invalid status message contains");

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
                             new CreateTransferListingRequestBodyDTO().playerId(1L)),
                     "TransferListingControllerV2::createTransferListing player already transfer listed throws");

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_NON_UNIQUE_TRANSFER_LISTING.getMessage()),
               "TransferListingControllerV2::createTransferListing player already transfer listed message contains");
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

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferListingApi.deleteTransferListing(1L),
                     "TransferListingControllerV2::deleteTransferListing unauthorized throws");
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for forbidden.
     */
    @Test
    void testDeleteTransferListingForbidden() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertThrows(FeignException.Forbidden.class,
                     () -> transferListingApi.deleteTransferListing(12L),
                     "TransferListingControllerV2::deleteTransferListing forbidden throws");
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for transferListing not found.
     */
    @Test
    void testDeleteTransferListingTransferListingNotFound() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertThrows(FeignException.NotFound.class,
                     () -> transferListingApi.deleteTransferListing(1_000L),
                     "TransferListingControllerV2::deleteTransferListing transferListing not found throws");
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for transfer day invalid status as user.
     */
    @Test
    void testDeleteTransferListingTransferDayInvalidStatusUser() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        final FeignException.Conflict e = assertThrows(FeignException.Conflict.class,
                     () -> transferListingApi.deleteTransferListing(13L),
                     "TransferListingControllerV2::deleteTransferListing transferDay invalid status user throws");

        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS.getMessage()),
               "TransferListingControllerV2::deleteTransferListing transferDay invalid status user message contains");
    }

    /**
     * Tests TransferListingControllerV2::deleteTransferListing for transfer day invalid status as admin.
     */
    @Test
    void testDeleteTransferListingTransferDayInvalidStatusAdmin() {
        final TransferListingApi transferListingApi = getAdministratorApi(TransferListingApi.class);

        assertDoesNotThrow(() -> transferListingApi.deleteTransferListing(13L),
                 "TransferListingControllerV2::deleteTransferListing transferDay invalid status admin does not throw");

        assertFalse(this.transferListingRepository.findById(13L).isPresent(),
                    "TransferListingControllerV2::deleteTransferListing transferDay invalid status admin isPresent");
    }

    /**
     * Tests TransferListingService::deleteTransferListing as user.
     */
    @Test
    void testDeleteTransferListingUser() {
        final TransferListingApi transferListingApi = getUserApi(TransferListingApi.class);

        assertDoesNotThrow(() -> transferListingApi.deleteTransferListing(14L),
                           "TransferListingControllerV2::deleteTransferListing user does not throw");

        assertFalse(this.transferListingRepository.findById(14L).isPresent(),
                    "TransferListingControllerV2::deleteTransferListing user isPresent");
    }

    /**
     * Tests TransferListingService::deleteTransferListing as admin.
     */
    @Test
    void testDeleteTransferListingAdmin() {
        final TransferListingApi transferListingApi = getAdministratorApi(TransferListingApi.class);

        assertDoesNotThrow(() -> transferListingApi.deleteTransferListing(15L),
                           "TransferListingControllerV2::deleteTransferListing admin does not throw");

        assertFalse(this.transferListingRepository.findById(15L).isPresent(),
                    "TransferListingControllerV2::deleteTransferListing admin isPresent");
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
                     () -> transferListingApi.getTransferListingsByTransferDayId(null, 1),
                     "TransferListingControllerV2::getTransferListingsByTransferDayId transferDayId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> transferListingApi.getTransferListingsByTransferDayId(1L, null),
                     "TransferListingControllerV2::getTransferListingsByTransferDayId page null throws");

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertTrue(transferDays.size() > 1,
                   "TransferListingControllerV2::getTransferListingsByTransferDayId transferDays size > 0");

        int pendingCount = 0;
        int notPendingCount = 0;

        for (final TransferDay transferDay : transferDays) {
            final TransferListingsResponseBodyDTO transferListingsResponseBodyDTO =
                    transferListingApi.getTransferListingsByTransferDayId(transferDay.getId(), 0);
            assertNotNull(transferListingsResponseBodyDTO,
                          "TransferListingControllerV2::getTransferListingsByTransferDayId response not null");

            final List<TransferListingDTO> result = transferListingsResponseBodyDTO.getTransferListings();

            assertNotNull(result, "TransferListingControllerV2::getTransferListingsByTransferDayId not null ");

            if (Status.PENDING.equals(transferDay.getStatus())) {
                assertTrue(result.isEmpty(),
                           "TransferListingControllerV2::getTransferListingsByTransferDayId pending empty");
                assertFalse(transferDay.getTransferListings().isEmpty(),
                           "TransferListingControllerV2::getTransferListingsByTransferDayId test data not empty");
                ++pendingCount;
            } else {
                assertFalse(result.isEmpty(), "TransferListingControllerV2::getTransferListingsByTransferDayId empty");
                assertEquals(map(transferDay.getTransferListings(), TransferListingDTO.class), result,
                             "TransferListingControllerV2::getTransferListingsByTransferDayId equals");
                ++notPendingCount;
            }
        }

        // Make sure that we have both pending and not pending test transfer days
        assertTrue(pendingCount > 0 && notPendingCount > 0,
                   "TransferListingControllerV2::getTransferListingsByTransferDayId pending and not pending count > 0");
    }

    /**
     * Checks a player season stat and a transfer listing DTO for equality.
     *
     * @param playerSeasonStat The player season stat.
     * @param result The result.
     */
    private void assertResultEquals(final PlayerSeasonStat playerSeasonStat, final TransferListingDTO result) {
        assertEquals(playerSeasonStat.getPlayer().getId(), result.getPlayer().getId(),
                     "TransferListingControllerV2::createTransferListing result player equals");
        assertEquals(playerSeasonStat.getTeam().getId(), result.getTeam().getId(),
                     "TransferListingControllerV2::createTransferListing result team equals");
        assertEquals(playerSeasonStat.getD11Team().getId(), result.getD11Team().getId(),
                     "TransferListingControllerV2::createTransferListing result position equals");
        assertEquals(playerSeasonStat.getPosition().getId(), result.getPosition().getId(),
                     "TransferListingControllerV2::createTransferListing result position equals");
        assertEquals(playerSeasonStat.getFormPoints(), result.getFormPoints(),
                     "TransferListingControllerV2::createTransferListing result formPoints equals");
        assertEquals(playerSeasonStat.getFormMatchPoints(), result.getFormMatchPoints(),
                     "TransferListingControllerV2::createTransferListing result formMatchPoints equals");
        assertEquals(playerSeasonStat.getRanking(), result.getRanking(),
                     "TransferListingControllerV2::createTransferListing result ranking equals");
        assertEquals(playerSeasonStat.getPointsPerAppearance(), result.getPointsPerAppearance(),
                     "TransferListingControllerV2::createTransferListing result pointsPerAppearance equals");
        assertEquals(playerSeasonStat.getCleanSheets(), result.getCleanSheets(),
                     "TransferListingControllerV2::createTransferListing result cleanSheets equals");
        assertEquals(playerSeasonStat.getYellowCards(), result.getYellowCards(),
                     "TransferListingControllerV2::createTransferListing result yellowCards equals");
        assertEquals(playerSeasonStat.getRedCards(), result.getRedCards(),
                     "TransferListingControllerV2::createTransferListing result redCards equals");
        assertEquals(playerSeasonStat.getSubstitutionsOn(), result.getSubstitutionsOn(),
                     "TransferListingControllerV2::createTransferListing result substitutionsOn equals");
        assertEquals(playerSeasonStat.getSubstitutionsOff(), result.getSubstitutionsOff(),
                     "TransferListingControllerV2::createTransferListing result substitutionsOff equals");
        assertEquals(playerSeasonStat.getManOfTheMatch(), result.getManOfTheMatch(),
                     "TransferListingControllerV2::createTransferListing result manOfTheMatch equals");
        assertEquals(playerSeasonStat.getSharedManOfTheMatch(), result.getSharedManOfTheMatch(),
                     "TransferListingControllerV2::createTransferListing result sharedManOfTheMatch equals");
        assertEquals(playerSeasonStat.getGamesStarted(), result.getGamesStarted(),
                     "TransferListingControllerV2::createTransferListing result gamesStarted equals");
        assertEquals(playerSeasonStat.getGamesSubstitute(), result.getGamesSubstitute(),
                     "TransferListingControllerV2::createTransferListing result gamesSubstitute equals");
        assertEquals(playerSeasonStat.getGamesDidNotParticipate(), result.getGamesDidNotParticipate(),
                     "TransferListingControllerV2::createTransferListing result gamesDidNotParticipate equals");
        assertEquals(playerSeasonStat.getMinutesPlayed(), result.getMinutesPlayed(),
                     "TransferListingControllerV2::createTransferListing result minutesPlayed equals");
        assertEquals(playerSeasonStat.getGoals(), result.getGoals(),
                     "TransferListingControllerV2::createTransferListing result goals equals");
        assertEquals(playerSeasonStat.getGoalAssists(), result.getGoalAssists(),
                     "TransferListingControllerV2::createTransferListing result goalAssists equals");
        assertEquals(playerSeasonStat.getOwnGoals(), result.getOwnGoals(),
                     "TransferListingControllerV2::createTransferListing result ownGoals equals");
        assertEquals(playerSeasonStat.getGoalsConceded(), result.getGoalsConceded(),
                     "TransferListingControllerV2::createTransferListing result goalsConceded equals");
        assertEquals(playerSeasonStat.getRating(), result.getRating(),
                     "TransferListingControllerV2::createTransferListing result rating equals");
        assertEquals(playerSeasonStat.getPoints(), result.getPoints(),
                     "TransferListingControllerV2::createTransferListing result points equals");
    }

}
