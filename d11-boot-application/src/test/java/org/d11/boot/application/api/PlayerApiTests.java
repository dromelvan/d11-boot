package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.api.service.PlayerApiService;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Player API tests.
 */
public class PlayerApiTests extends AbstractRepositoryApiTests<Player, PlayerRepository, PlayerApiService> {

    /**
     * Tests the findPlayerById API operation.
     */
    @Test
    public void findPlayerById() {
        for(final Player player : getEntities()) {
            final PlayerDTO result = getApiService().findPlayerById(player.getId());
            final PlayerDTO playerDTO = map(player, PlayerDTO.class);

            assertNotNull(result, "Player by id should not be null.");
            assertEquals(playerDTO, result, "Player by id should equal Player.");
        }

        assertNull(getApiService().findPlayerById(-1L), "Player not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
