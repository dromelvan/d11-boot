package org.d11.boot.application.api;

import org.d11.boot.api.model.InsertPlayerDTO;
import org.d11.boot.api.model.InsertPlayerResultDTO;
import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.api.model.UpdatePlayerDTO;
import org.d11.boot.api.service.D11ApiServiceException;
import org.d11.boot.api.service.PlayerApiService;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.PlayerSeasonStat;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
public class PlayerApiTests extends AbstractRepositoryApiTests<Player, PlayerRepository, PlayerApiService> {

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
        for(final Player player : getRepository().findAll()) {
            final PlayerDTO result = getApiService().findPlayerById(player.getId());
            final PlayerDTO playerDTO = map(player, PlayerDTO.class);

            assertNotNull(result, "Player by id should not be null.");
            assertEquals(playerDTO, result, "Player by id should equal Player.");
        }

        assertNull(getApiService().findPlayerById(-1L), "Player not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the insertPlayer operation.
     */
    @Test
    public void insertPlayer() {
        final InsertPlayerDTO insertPlayerDTO = new InsertPlayerDTO();
        final D11ApiServiceException d11ApiServiceException =
                assertThrows(D11ApiServiceException.class, () -> getApiService().insertPlayer(insertPlayerDTO));
        assertEquals(HttpStatus.BAD_REQUEST, d11ApiServiceException.getStatusCode(),
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
        InsertPlayerResultDTO insertPlayerResultDTO = getApiService().insertPlayer(insertPlayerDTO);

        assertNotNull(insertPlayerResultDTO, "Invalid input result should not be null.");
        assertNull(insertPlayerResultDTO.getPlayerId(), "Invalid input result player id should be null.");
        assertNull(insertPlayerResultDTO.getPlayerSeasonStatId(),
                "Invalid input result player season stat id should be null.");
        assertFalse(insertPlayerResultDTO.getErrors().isEmpty(), "Invalid input result errors should not be empty.");

        insertPlayerDTO
                .firstName("FirstName")
                .lastName("LastName");

        insertPlayerResultDTO = getApiService().insertPlayer(insertPlayerDTO);

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
        final D11ApiServiceException d11ApiServiceException =
                assertThrows(D11ApiServiceException.class, () -> getApiService().updatePlayer(updatePlayerDTO));
        assertEquals(HttpStatus.BAD_REQUEST, d11ApiServiceException.getStatusCode(),
                "Update player request with missing properties should result in BAD_REQUEST.");

        // Add successful tests when we can be bothered figuring out how to not mess up other tests with new data.
    }

}
