package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.D11TeamApi;
import org.d11.boot.api.v2.model.D11TeamDTO;
import org.d11.boot.api.v2.model.D11TeamResponseBodyDTO;
import org.d11.boot.api.v2.model.D11TeamsResponseBodyDTO;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * D11 team controller tests.
 */
class D11TeamControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Team repository.
     */
    @Autowired
    private D11TeamRepository d11TeamRepository;

    /**
     * Tests D11TeamController::getD11Teams.
     */
    @Test
    void testGetTeams() {
        final D11TeamApi d11TeamApi = getApi(D11TeamApi.class);

        final List<D11Team> d11Teams = this.d11TeamRepository.findByOrderByName();
        assertFalse(d11Teams.isEmpty(), "D11TeamController::getD11Teams not empty");

        final D11TeamsResponseBodyDTO d11TeamsResponseBodyDTO = d11TeamApi.getD11Teams();

        assertNotNull(d11TeamsResponseBodyDTO, "D11TeamController::getD11Teams not null");

        final List<D11TeamDTO> result = d11TeamsResponseBodyDTO.getD11Teams();

        assertEquals(d11Teams.size(), result.size(), "D11TeamController::getD11Teams size");

        for (int i = 0; i < d11Teams.size(); ++i) {
            final D11Team d11Team = d11Teams.get(i);
            final D11TeamDTO d11TeamDTO = result.get(i);

            assertEquals(map(d11Team, D11TeamDTO.class), d11TeamDTO, "D11TeamController::getD11Teams equals");
        }
    }

    /**
     * Tests D11TeamController::getD11TeamById.
     */
    @Test
    void testGetTeamById() {
        final D11TeamApi d11TeamApi = getApi(D11TeamApi.class);

        assertThrows(FeignException.NotFound.class, () -> d11TeamApi.getD11TeamById(0L),
                     "D11TeamController::getD11TeamById not found");

        final List<D11Team> d11Teams = this.d11TeamRepository.findAll();

        assertFalse(d11Teams.isEmpty(), "D11TeamController::getD11TeamById players not empty");

        for (final D11Team d11Team : d11Teams) {
            final D11TeamResponseBodyDTO result = d11TeamApi.getD11TeamById(d11Team.getId());
            assertNotNull(result, "D11TeamController::getD11TeamById not null");
            assertEquals(getMapper().map(d11Team, D11TeamDTO.class), result.getD11Team(),
                    "D11TeamController::getD11TeamById equals");
        }
    }

}
