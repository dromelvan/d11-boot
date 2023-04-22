package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.InsertPlayerDTO;
import org.d11.boot.api.model.InsertPlayerResultDTO;
import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.api.model.PlayerTransferStatusDTO;
import org.d11.boot.api.model.UpdatePlayerDTO;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.client.api.PlayerApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player API tests.
 */
public class PlayerApiTests extends AbstractRepositoryApiTests<Player, PlayerRepository> {

    /**
     * Player repository used for player admin operation tests.
     */
    @Autowired
    private PlayerRepository playerRepository;
    /**
     * Player season stat repository used for player admin operation tests.
     */
    @Autowired
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Tests the findPlayerById API operation.
     */
    @Test
    public void findPlayerById() {
        final PlayerApi playerApi = getApi(PlayerApi.class);
        for(final Player player : getRepository().findAll()) {
            final PlayerDTO result = playerApi.findPlayerById(player.getId());
            final PlayerDTO playerDTO = map(player, PlayerDTO.class);

            assertNotNull(result, "Player by id should not be null.");
            assertEquals(playerDTO, result, "Player by id should equal Player.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> playerApi.findPlayerById(-1L),
                     "Player not found should throw NotFound exception.");
    }

    /**
     * Tests the findPlayerByPremierLeagueId API operation.
     */
    @Test
    public void findPlayerByPremierLeagueId() {
        final PlayerApi playerApi = getApi(PlayerApi.class);
        for(final Player player : getRepository().findAll()) {
            final PlayerDTO result = playerApi.findPlayerByPremierLeagueId(player.getPremierLeagueId());
            final PlayerDTO playerDTO = map(player, PlayerDTO.class);

            assertNotNull(result, "Player by Premier League id should not be null.");
            assertEquals(playerDTO, result, "Player by Premier League id should equal Player.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> playerApi.findPlayerByPremierLeagueId(-1),
                     "Player by Premier League id not found should throw NotFound exception.");
    }

    /**
     * Tests the insertPlayer operation.
     */
    @Test
    public void insertPlayer() {
        final InsertPlayerDTO insertPlayerDTO = new InsertPlayerDTO();
        assertThrows(FeignException.BadRequest.class,
                     () -> getApi(PlayerApi.class).insertPlayer(insertPlayerDTO),
                     "Insert player request with missing properties should result in BAD_REQUEST.");

        insertPlayerDTO
                .firstName("")
                .lastName("")
                .fullName("")
                .teamId(1L)
                .countryId(1L)
                .positionId(1L)
                .whoscoredId(1L)
                .dateOfBirth(LocalDate.now())
                .height(1);

        assertThrows(FeignException.Forbidden.class,
                     () -> getApi(PlayerApi.class).insertPlayer(insertPlayerDTO),
                     "Insert player request without authorization should result in FORBIDDEN.");

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(PlayerApi.class).insertPlayer(insertPlayerDTO),
                     "Insert player request with user authorization should result in FORBIDDEN.");

        final PlayerApi playerApi = getAdministratorApi(PlayerApi.class);
        InsertPlayerResultDTO insertPlayerResultDTO = playerApi.insertPlayer(insertPlayerDTO);

        assertNotNull(insertPlayerResultDTO, "Invalid input result should not be null.");
        assertNull(insertPlayerResultDTO.getPlayerId(), "Invalid input result player id should be null.");
        assertNull(insertPlayerResultDTO.getPlayerSeasonStatId(),
                "Invalid input result player season stat id should be null.");
        assertFalse(insertPlayerResultDTO.getErrors().isEmpty(), "Invalid input result errors should not be empty.");

        insertPlayerDTO
                .firstName("FirstName")
                .lastName("LastName");

        insertPlayerResultDTO = playerApi.insertPlayer(insertPlayerDTO);

        assertNotNull(insertPlayerResultDTO, "Valid input result should not be null.");
        assertNotNull(insertPlayerResultDTO.getPlayerId(), "Valid input result player id should not be null.");
        assertNotNull(insertPlayerResultDTO.getPlayerSeasonStatId(),
                "Valid input result player season stat id should not be null.");
        assertTrue(insertPlayerResultDTO.getErrors().isEmpty(), "Valid input errors should be empty.");

        final Player player = this.playerRepository.findById(insertPlayerResultDTO.getPlayerId()).orElse(null);
        assertNotNull(player, "Inserted player should not be null.");
        final PlayerSeasonStat playerSeasonStat =
                this.playerSeasonStatRepository.findById(insertPlayerResultDTO.getPlayerSeasonStatId()).orElse(null);
        assertNotNull(playerSeasonStat, "Inserted player season stat should not be null.");

        this.playerRepository.delete(player);
        this.playerSeasonStatRepository.delete(playerSeasonStat);
    }

    /**
     * Tests the updatePlayer operation.
     */
    @Test
    public void updatePlayer() {
        final UpdatePlayerDTO updatePlayerDTO = new UpdatePlayerDTO();
        assertThrows(FeignException.BadRequest.class,
                     () -> getApi(PlayerApi.class).updatePlayer(updatePlayerDTO),
                     "Update player request with missing properties should result in BAD_REQUEST.");

        updatePlayerDTO
                .id(1L)
                .firstName("Firstname")
                .lastName("Lastname")
                .fullName("")
                .teamId(1L)
                .d11TeamId(1L)
                .countryId(1L)
                .positionId(1L)
                .whoscoredId(1L)
                .dateOfBirth(LocalDate.now())
                .height(1);

        assertThrows(FeignException.Forbidden.class,
                     () -> getApi(PlayerApi.class).updatePlayer(updatePlayerDTO),
                     "Update player request without authorization should result in FORBIDDEN.");

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(PlayerApi.class).updatePlayer(updatePlayerDTO),
                     "Update player request with user authorization should result in FORBIDDEN.");

        // Add successful tests when we can be bothered figuring out how to not mess up other tests with new data.
    }

    /**
     * Tests the findPlayerTransferStatusById operation.
     */
    @Test
    public void findPlayerTransferStatusById() {
        final long nonTransferListedPlayerId = 1L;
        final long transferListedPlayerId = 2L;

        assertThrows(FeignException.NotFound.class,
                () -> getUserApi(PlayerApi.class).findPlayerTransferStatusById(0L),
                "Find player transfer status non existent player should result in NOT_FOUND.");

        PlayerTransferStatusDTO playerTransferStatusDTO = getApi(PlayerApi.class).findPlayerTransferStatusById(transferListedPlayerId);
        assertEquals(transferListedPlayerId, playerTransferStatusDTO.getId(), "Not logged in id should equal player id.");
        assertFalse(playerTransferStatusDTO.isTransferListable(), "Not logged in transfer listable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferListRemovable(), "Not logged in transfer list removable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBiddable(), "Not logged in transfer biddable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBidRemovable(), "Not logged in transfer bid removable should be false.");

        playerTransferStatusDTO = getUserApi(PlayerApi.class).findPlayerTransferStatusById(transferListedPlayerId);
        assertEquals(transferListedPlayerId, playerTransferStatusDTO.getId(), "Non owner id should equal player id.");
        assertFalse(playerTransferStatusDTO.isTransferListable(), "Non owner transfer listable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferListRemovable(), "Non owner transfer list removable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBiddable(), "Non owner transfer biddable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBidRemovable(), "Non owner transfer bid removable should be false.");

        playerTransferStatusDTO = getUserApi(PlayerApi.class).findPlayerTransferStatusById(nonTransferListedPlayerId);
        assertEquals(nonTransferListedPlayerId, playerTransferStatusDTO.getId(), "Not transfer listed id should equal player id.");
        assertTrue(playerTransferStatusDTO.isTransferListable(), "Not transfer listed transfer listable should be true.");
        assertFalse(playerTransferStatusDTO.isTransferListRemovable(), "Not transfer listed transfer list removable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBiddable(), "Nnt transfer listed transfer biddable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBidRemovable(), "Not transfer listed transfer bid removable should be false.");

        playerTransferStatusDTO = getAdministratorApi(PlayerApi.class).findPlayerTransferStatusById(transferListedPlayerId);
        assertEquals(transferListedPlayerId, playerTransferStatusDTO.getId(), "Transfer listed id should equal player id.");
        assertFalse(playerTransferStatusDTO.isTransferListable(), "Transfer listed transfer listable should be false.");
        assertTrue(playerTransferStatusDTO.isTransferListRemovable(), "Transfer listed transfer list removable should be true.");
        assertFalse(playerTransferStatusDTO.isTransferBiddable(), "Transfer listed transfer biddable should be false.");
        assertFalse(playerTransferStatusDTO.isTransferBidRemovable(), "Transfer listed transfer bid removable should be false.");

        // TODO Add more tests when we figure out how to get proper test data
    }

}
