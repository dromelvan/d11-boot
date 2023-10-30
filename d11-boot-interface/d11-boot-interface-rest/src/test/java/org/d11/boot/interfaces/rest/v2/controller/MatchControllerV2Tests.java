package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.MatchApi;
import org.d11.boot.api.v2.model.MatchDTO;
import org.d11.boot.api.v2.model.MatchResponseBodyDTO;
import org.d11.boot.api.v2.model.StadiumDTO;
import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Match controller tests.
 */
class MatchControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Match repository.
     */
    @Autowired
    private MatchRepository matchRepository;

    /**
     * Tests MatchController::getMatchById.
     */
    @Test
    void testGetSeasonById() {
        final MatchApi matchApi = getApi(MatchApi.class);
        final List<Match> matches = this.matchRepository.findAll();

        assertFalse(matches.isEmpty(), "MatchController::getMatchById empty");

        for (final Long matchId : matches.stream().map(D11Entity::getId).toList()) {
            final MatchResponseBodyDTO result = matchApi.getMatchById(matchId);
            assertNotNull(result, "MatchController::getMatchById not null");

            // Have to do findById to get the associations needed for the mapping
            final Match match = this.matchRepository.findById(matchId).orElseThrow(IllegalStateException::new);

            assertEquals(getMapper().map(match, MatchDTO.class),
                         result.getMatch(),
                         "MatchController::getMatchById equals");
            assertEquals(getMapper().map(match.getStadium(), StadiumDTO.class),
                         result.getStadium(),
                         "MatchController::getMatchById stadium equals");
        }

        assertThrows(FeignException.NotFound.class, () -> matchApi.getMatchById(0L),
                     "MatchController::getMatchById not found");
    }

}
