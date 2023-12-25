package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerApi;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultsResponseBodyDTO;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player controller tests.
 */
class PlayerControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Tests PlayerController::searchPlayersByName.
     */
    @Test
    void testSearchPlayersByName() {
        final PlayerApi playerApi = getApi(PlayerApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.searchPlayersByName((String) null),
                     "PlayerController::searchPlayersByName name null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.searchPlayersByName(""),
                     "PlayerController::searchPlayersByName name empty throws");

        // There are players named Bar Foo, Foo and Foo Bar available for this

        final List<PlayerSearchResult> likeFooPlayers = this.playerRepository.findByParameterizedNameLike("foo");
        final PlayerSearchResultsResponseBodyDTO likeFooResponse = playerApi.searchPlayersByName("Foo");

        assertNotNull(likeFooResponse, "PlayerController::searchPlayersByName like foo response not null");
        assertEquals(3, likeFooResponse.getPlayers().size(),
                    "PlayerController::searchPlayersByName like foo response size");
        assertEquals(getMapper().map(likeFooPlayers, PlayerSearchResultDTO.class), likeFooResponse.getPlayers(),
                     "PlayerController::searchPlayersByName like foo response equals");

        final List<PlayerSearchResult> exactFooPlayers = this.playerRepository.findByParameterizedNameExact("foo");
        final PlayerSearchResultsResponseBodyDTO exactFooResponse = playerApi.searchPlayersByName("\"Foo\"");

        assertNotNull(exactFooResponse, "PlayerController::searchPlayersByName exact foo response not null");
        assertEquals(1, exactFooResponse.getPlayers().size(),
                     "PlayerController::searchPlayersByName exact foo response size");
        assertEquals(getMapper().map(exactFooPlayers, PlayerSearchResultDTO.class), exactFooResponse.getPlayers(),
                     "PlayerController::searchPlayersByName exact foo response equals");

        final List<PlayerSearchResult> likeBarPlayers = this.playerRepository.findByParameterizedNameLike("bar");
        final PlayerSearchResultsResponseBodyDTO likeBarResponse = playerApi.searchPlayersByName("Bar");

        assertNotNull(likeBarResponse, "PlayerController::searchPlayersByName like bar response not null");
        assertEquals(2, likeBarResponse.getPlayers().size(),
                     "PlayerController::searchPlayersByName like bar response size");
        assertEquals(getMapper().map(likeBarPlayers, PlayerSearchResultDTO.class), likeBarResponse.getPlayers(),
                     "PlayerController::searchPlayersByName like bar response equals");

        final PlayerSearchResultsResponseBodyDTO exactBarResponse = playerApi.searchPlayersByName("\"Bar\"");

        assertNotNull(exactBarResponse, "PlayerController::searchPlayersByName exact bar response not null");
        assertTrue(exactBarResponse.getPlayers().isEmpty(),
                   "PlayerController::searchPlayersByName exact bar response empty");
    }

}
