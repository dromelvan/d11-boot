package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.client.api.PlayerApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Player API tests.
 */
public class PlayerApiTests extends AbstractApiTests {

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;
    /**
     * List of players.
     */
    private List<Player> players;

    /**
     * Sets up mocked players for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.players = this.playerRepository.findAll();
    }

    /**
     * Tests the findPlayerById API operation.
     */
    @Test
    public void findPlayerById() {
        final PlayerApi playerApi = new PlayerApi(getApiClient());

        for(final Player player : this.players) {
            final PlayerDTO result = playerApi.findPlayerById(player.getId()).block();
            final PlayerDTO playerDTO = map(player, PlayerDTO.class);

            assertNotNull(result, "Player by id should not be null.");
            assertEquals(playerDTO, result, "Player by id should equal Player.");
        }

        assertNotFound(playerApi.findPlayerById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
