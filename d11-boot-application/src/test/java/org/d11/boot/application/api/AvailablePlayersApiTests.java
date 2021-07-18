package org.d11.boot.application.api;

import org.d11.boot.api.model.AvailablePlayersTeamDTO;
import org.d11.boot.api.service.AvailablePlayerApiService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Available players API tests.
 */
public class AvailablePlayersApiTests extends AbstractApiTests<AvailablePlayerApiService> {

    /**
     * Tests the findAvailablePlayers API operation.
     */
    @Test
    public void findAvailablePlayers() {
        final List<AvailablePlayersTeamDTO> availablePlayersTeams = getApiService().findAvailablePlayers();
        assertNotNull(availablePlayersTeams, "Available players should not be null.");
        assertFalse(availablePlayersTeams.isEmpty(), "Available players should not be empty.");
    }

}
