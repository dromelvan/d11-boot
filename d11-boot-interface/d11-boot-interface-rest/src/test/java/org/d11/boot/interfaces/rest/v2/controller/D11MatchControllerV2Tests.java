package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.D11MatchApi;
import org.d11.boot.api.v2.model.D11MatchDTO;
import org.d11.boot.api.v2.model.D11MatchResponseBodyDTO;
import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.repository.D11MatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * D11 match controller tests.
 */
class D11MatchControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * D11 match repository.
     */
    @Autowired
    private D11MatchRepository d11MatchRepository;

    /**
     * Tests D11MatchController::getD11MatchById.
     */
    @Test
    void testGetD11MatchById() {
        final D11MatchApi d11MatchApi = getApi(D11MatchApi.class);
        final List<D11Match> d11Matches = this.d11MatchRepository.findAll();

        assertFalse(d11Matches.isEmpty(), "D11MatchController::getD11MatchById empty");

        for (final Long d11MatchId : d11Matches.stream().map(D11Entity::getId).toList()) {
            final D11MatchResponseBodyDTO result = d11MatchApi.getD11MatchById(d11MatchId);
            assertNotNull(result, "D11MatchController::getD11MatchById not null");

            // Have to do findById to get the associations needed for the mapping
            final D11Match d11Match = this.d11MatchRepository
                    .findById(d11MatchId).orElseThrow(IllegalStateException::new);

            assertEquals(getMapper().map(d11Match, D11MatchDTO.class),
                         result.getD11Match(),
                         "D11MatchController::getD11MatchById equals");
        }

        assertThrows(FeignException.NotFound.class, () -> d11MatchApi.getD11MatchById(0L),
                     "D11MatchController::getD11MatchById not found");
    }

}
