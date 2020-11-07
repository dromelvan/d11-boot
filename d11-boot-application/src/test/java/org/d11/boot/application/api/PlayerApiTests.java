package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.client.api.PlayerApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Player API tests.
 */
public class PlayerApiTests extends AbstractApiTests<Player, PlayerRepository> {

    /**
     * Tests the findPlayerById API operation.
     */
    @Test
    public void findPlayerById() {
        final PlayerApi playerApi = new PlayerApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Players should not be empty.");

        for(final Player player : getEntities()) {
            final PlayerDTO result = playerApi.findPlayerById(player.getId()).block();
            final PlayerDTO playerDTO = map(player, PlayerDTO.class);

            assertNotNull(result, "Player by id should not be null.");
            assertEquals(playerDTO, result, "Player by id should equal Player.");
        }

        assertNotFound(playerApi.findPlayerById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
