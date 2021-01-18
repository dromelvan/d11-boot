package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.api.service.PlayerSeasonStatApiService;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Player season stat API tests.
 */
public class PlayerSeasonStatApiTests extends AbstractRepositoryApiTests<PlayerSeasonStat, PlayerSeasonStatRepository, PlayerSeasonStatApiService> {

    /**
     * Tests the findPlayerSeasonStatById API operation.
     */
    @Test
    public void findPlayerSeasonStatById() {
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final PlayerSeasonStatDTO result = getApiService().findPlayerSeasonStatById(playerSeasonStat.getId());
            final PlayerSeasonStatDTO playerSeasonStatDTO = map(playerSeasonStat, PlayerSeasonStatDTO.class);

            assertNotNull(result, "Player season stat by id should not be null.");
            assertEquals(playerSeasonStatDTO, result, "Player season stat by id should equal PlayerSeasonStat.");
        }

        assertNull(getApiService().findPlayerSeasonStatById(-1L), "Player season stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
